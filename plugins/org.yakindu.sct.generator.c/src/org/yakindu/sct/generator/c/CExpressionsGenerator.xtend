package org.yakindu.sct.generator.c

import javax.inject.Inject
import org.yakindu.base.expressions.expressions.BoolLiteral
import org.yakindu.base.expressions.expressions.ElementReferenceExpression
import org.yakindu.base.expressions.expressions.Expression
import org.yakindu.base.expressions.expressions.FeatureCall
import org.yakindu.base.expressions.expressions.LogicalAndExpression
import org.yakindu.base.expressions.expressions.LogicalNotExpression
import org.yakindu.base.expressions.expressions.LogicalOrExpression
import org.yakindu.base.expressions.expressions.LogicalRelationExpression
import org.yakindu.base.expressions.expressions.NullLiteral
import org.yakindu.base.types.Enumerator
import org.yakindu.base.types.Event
import org.yakindu.base.types.Operation
import org.yakindu.base.types.Property
import org.yakindu.base.types.inferrer.ITypeSystemInferrer
import org.yakindu.base.types.typesystem.ITypeSystem
import org.yakindu.sct.generator.core.templates.ExpressionsGenerator
import org.yakindu.sct.generator.core.types.ICodegenTypeSystemAccess
import org.yakindu.sct.model.sexec.naming.INamingService
import org.yakindu.sct.model.stext.stext.ActiveStateReferenceExpression
import org.yakindu.sct.model.stext.stext.EventRaisingExpression
import org.yakindu.sct.model.stext.stext.EventValueReferenceExpression
import org.yakindu.sct.model.stext.stext.OperationDefinition
import org.yakindu.sct.model.stext.stext.VariableDefinition

class CExpressionsGenerator extends ExpressionsGenerator {

	@Inject protected extension Naming
	@Inject protected extension Navigation
	
	@Inject protected extension ITypeSystem
	@Inject protected extension ITypeSystemInferrer
	@Inject protected extension INamingService
	@Inject protected extension ICodegenTypeSystemAccess

	/* Referring to declared elements */
	def dispatch CharSequence code(ElementReferenceExpression it) {
		it.code(it.definition)
	}

	def dispatch CharSequence code(FeatureCall it) {
		it.code(it.definition)
	}
	
	def dispatch CharSequence code(NullLiteral it) '''«Naming::NULL_STRING»'''
	
	def dispatch CharSequence code(Expression it, Event target) '''«target.access»'''

	def dispatch CharSequence code(Expression it, VariableDefinition target) '''«target.access»'''
	
	/* TODO: check if event is active */
	def dispatch CharSequence code(EventValueReferenceExpression it) '''«value.definition.event.valueAccess»'''

	def dispatch CharSequence code(ElementReferenceExpression it, VariableDefinition target) '''«target.access»'''

	def dispatch CharSequence code(FeatureCall it, VariableDefinition target) '''«target.access»'''

	def dispatch CharSequence code(ElementReferenceExpression it,
		OperationDefinition target) '''«target.access»(«scHandle»«FOR arg : expressions BEFORE ', ' SEPARATOR ', '»«arg.
		code»«ENDFOR»)'''

	def dispatch CharSequence code(ElementReferenceExpression it,
		Operation target) '''«target.access»(«FOR arg : expressions SEPARATOR ', '»«arg.code»«ENDFOR»)'''

	def dispatch CharSequence code(ElementReferenceExpression it, Property target) '''«target.access»'''

	def dispatch CharSequence code(FeatureCall it,
		OperationDefinition target) '''«target.access»(«scHandle»«FOR arg : expressions BEFORE ', ' SEPARATOR ', '»«arg.
		code»«ENDFOR»)'''

	def dispatch CharSequence code(FeatureCall it,
		Operation target) '''«it.owner.code».«target.access»(«FOR arg : expressions SEPARATOR ', '»«arg.
		code»«ENDFOR»)'''

	def dispatch CharSequence code(FeatureCall it, Property target) '''«it.owner.code».«target.access»'''

	def dispatch CharSequence code(FeatureCall it, Enumerator target) '''«target.access»'''

	def dispatch CharSequence code(BoolLiteral it) '''«IF value»bool_true«ELSE»bool_false«ENDIF»'''

	def dispatch CharSequence code(EventRaisingExpression it) '''
	«IF value != null»
		«event.definition.event.valueAccess» = «value.code»;
	«ENDIF»
	«event.definition.event.access» = bool_true'''

	def dispatch CharSequence code(
		ActiveStateReferenceExpression it) '''«flow.stateActiveFctID»(«scHandle», «value.shortName»)'''

	// ensure we obtain an expression of type sc_boolean
	def dispatch CharSequence sc_boolean_code(Expression it) '''«it.code»'''

	def dispatch CharSequence sc_boolean_code(LogicalOrExpression it) '''(«it.code») ? bool_true : bool_false'''

	def dispatch CharSequence sc_boolean_code(LogicalAndExpression it) '''(«it.code») ? bool_true : bool_false'''

	def dispatch CharSequence sc_boolean_code(LogicalNotExpression it) '''(«it.code») ? bool_true : bool_false'''

	def dispatch CharSequence sc_boolean_code(LogicalRelationExpression it) '''(«it.code») ? bool_true : bool_false'''


}