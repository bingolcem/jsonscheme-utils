package com.hemosoft.jsonSchema.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.InstanceAttributeOverrideV2;
import com.github.victools.jsonschema.generator.SchemaGenerationContext;
import com.github.victools.jsonschema.generator.SchemaKeyword;

class MandatoryCompanyPropertyAttributeOverride implements InstanceAttributeOverrideV2<FieldScope> {

       

        // @SuppressWarnings("deprecation")
        // @Override
        // public void overrideTypeAttributes(ObjectNode schemaNode, TypeScope scope,
        // SchemaGenerationContext context) {
        // // if (scope.getType().getErasedType() != TestType.class) {
        // // return;
        // // }
        // ObjectNode allOfEntry =
        // schemaNode.withArray(context.getKeyword(SchemaKeyword.TAG_ALLOF)).addObject();
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_IF))
        // .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
        // .with("account")
        // .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
        // .with("type")
        // .put(context.getKeyword(SchemaKeyword.TAG_CONST), "COMPANY");
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_THEN))
        // .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
        // .add("account").add("person").add("company");
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_ELSE))
        // .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
        // .add("account").add("person");
        // }

        // public MandatoryCompanyPropertyAttributeOverride(FieldScope field) {
        // this.field = field;
        // }

        // @Override
        // public void overrideInstanceAttributes(ObjectNode collectedMemberAttributes,
        // MemberScope member,
        // SchemaGenerationContext context) {
        // FormField formField=
        // this.field.getAnnotationConsideringFieldAndGetter(FormField.class);

        // ObjectNode allOfEntry =
        // collectedMemberAttributes.withArray(context.getKeyword(SchemaKeyword.TAG_ALLOF)).addObject();
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_IF))
        // .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
        // .with("account")
        // .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
        // .with("type")
        // .put(context.getKeyword(SchemaKeyword.TAG_CONST), "COMPANY");
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_THEN))
        // .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
        // .add("account").add("person").add("company");
        // allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_ELSE))
        // .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
        // .add("account").add("person");
        // }

        @Override
        public void overrideInstanceAttributes(ObjectNode collectedMemberAttributes, FieldScope member,
                        SchemaGenerationContext context) {
                Conditional conditional = member.getAnnotationConsideringFieldAndGetter(Conditional.class);
                if (conditional != null) {
                        ObjectNode allOfEntry = collectedMemberAttributes
                                        .withArray(context.getKeyword(SchemaKeyword.TAG_ALLOF)).addObject();
                        allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_IF))
                                        .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
                                        .with("account")
                                        .with(context.getKeyword(SchemaKeyword.TAG_PROPERTIES))
                                        .with("type")
                                        .put(context.getKeyword(SchemaKeyword.TAG_CONST), "COMPANY");
                        allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_THEN))
                                        .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
                                        .add("account").add("person").add("company");
                        allOfEntry.with(context.getKeyword(SchemaKeyword.TAG_ELSE))
                                        .withArray(context.getKeyword(SchemaKeyword.TAG_REQUIRED))
                                        .add("account").add("person");
                }
        }
}
