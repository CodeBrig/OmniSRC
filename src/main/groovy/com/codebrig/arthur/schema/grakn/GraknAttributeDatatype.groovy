package com.codebrig.arthur.schema.grakn

/**
 * Used to determine the appropriate datatype
 * to store in Grakn for a given UAST attribute
 *
 * @version 0.4
 * @since 0.2
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 */
class GraknAttributeDatatype {

    static String getType(String attribute) {
        return "string"
    }
}
