package com.codebrig.omnisrc.observe.structure.naming

import com.codebrig.omnisrc.SourceNode
import com.codebrig.omnisrc.observe.filter.InternalRoleFilter
import com.codebrig.omnisrc.observe.structure.StructureNaming

/**
 * todo: this
 *
 * @version 0.3
 * @since 0.3
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 */
class GoNaming implements StructureNaming {

    @Override
    String getNodeName(SourceNode node) {
        switch (Objects.requireNonNull(node).internalType) {
            case "FuncDecl":
                return getFuncDeclName(node)
            default:
                throw new IllegalArgumentException("Unsupported Go node type: " + node.internalType)
        }
    }

    static String getFuncDeclName(SourceNode node) {
        def functionName = ""
        new InternalRoleFilter("Name").getFilteredNodes(node.children).each {
            functionName = it.token
        }
        return functionName + "()"
    }
}
