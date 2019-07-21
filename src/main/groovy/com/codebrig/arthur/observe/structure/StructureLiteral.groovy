package com.codebrig.arthur.observe.structure

import com.codebrig.arthur.SourceNode
import org.apache.commons.lang.StringEscapeUtils
import org.apache.commons.lang.math.NumberUtils

/**
 * Used to determine and get the literal type of UAST nodes
 *
 * @version 0.4
 * @since 0.2
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 * @author <a href="mailto:valpecaoco@gmail.com">Val Pecaoco</a>
 */
abstract class StructureLiteral {

    static long toLong(String value) {
        value = value.replace("_", "")
        try {
            if (value.toUpperCase().startsWith("0X")) {
                int i = 0
                if (value.toUpperCase().endsWith("L")) {
                    i = 1
                }
                return new BigInteger(value.substring(2, value.length() - i), 16).longValue()
            } else if (value.toUpperCase().startsWith("0B")) {
                int i = 0
                if (value.toUpperCase().endsWith("L")) {
                    i = 1
                }
                return new BigInteger(value.substring(2, value.length() - i), 2).longValue()
            } else if (value.startsWith("0")) {
                if (value.matches(/0[1-7]*(l|L)?/)) {
                    int i = 0
                    if (value.toUpperCase().endsWith("L")) {
                        i = 1
                    }
                    return new BigInteger(value.substring(1, value.length() - i), 8).longValue()
                }
            }
            if (value.toUpperCase().endsWith("L")) {
                return Long.decode(value.substring(0, value.length() - 1))
            }
            return Long.valueOf(value)
        } catch (Exception ex) {
            return NumberUtils.toLong(value)
        }
    }

    static double toDouble(String value) {
        value = value.replace("_", "")
        try {
            return Double.valueOf(value)
        } catch (Exception ex) {
            return NumberUtils.toDouble(value)
        }
    }

    boolean isNodeLiteral(SourceNode node) {
        return getNodeLiteralAttribute(node) != null
    }

    boolean isNegative(SourceNode node) {
        return node.parentSourceNode.children.any { it.roles.any { it.negative } }
    }

    Object getNodeLiteralValue(SourceNode node) {
        boolean isNegative = isNegative(node)
        switch (node.getLiteralAttribute()) {
            case numberValueLiteral():
                return toLong(((isNegative) ? "-" : "") + node.token)
            case doubleValueLiteral():
                return toDouble(((isNegative) ? "-" : "") + node.token)
            default:
                return StringEscapeUtils.escapeJava(node.token) //treat as string
        }
    }

    abstract String getNodeLiteralAttribute(SourceNode node)

    abstract List<String> getPossibleNodeLiteralAttributes(SourceNode node)

    static String numberValueLiteral() {
        return "numberValue"
    }

    static String doubleValueLiteral() {
        return "doubleValue"
    }

    static String booleanValueLiteral() {
        return "booleanValue"
    }

    static String stringValueLiteral() {
        return "stringValue"
    }

    static Map<String, String> getAllLiteralAttributes() {
        def rtnMap = new LinkedHashMap<String, String>()
        rtnMap.put(booleanValueLiteral(), "boolean")
        rtnMap.put(doubleValueLiteral(), "double")
        rtnMap.put("name", "string")
        rtnMap.put(numberValueLiteral(), "long")
        rtnMap.put("token", "string")
        return rtnMap
    }
}
