package com.codebrig.omnisrc.observe.filter

import com.codebrig.omnisrc.SourceNode
import com.codebrig.omnisrc.SourceNodeFilter

/**
 * todo: this
 *
 * @version 0.3
 * @since 0.3
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 */
class FunctionFilter extends SourceNodeFilter<FunctionFilter, Void> {

    private static final Set<String> functionTypes = new HashSet<>()
    static {
        functionTypes.add("FuncDecl") //go
        functionTypes.add("MethodDeclaration") //java
        functionTypes.add("FunctionDeclaration") //js
        functionTypes.add("FunctionDef") //python
    }

    @Override
    boolean evaluate(SourceNode node) {
        return node != null && node.internalType in functionTypes
    }
}