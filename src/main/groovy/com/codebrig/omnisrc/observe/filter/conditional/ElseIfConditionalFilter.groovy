package com.codebrig.omnisrc.observe.filter.conditional

import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.SourceNode
import com.codebrig.omnisrc.SourceNodeFilter
import com.codebrig.omnisrc.observe.filter.InternalRoleFilter
import com.codebrig.omnisrc.observe.filter.MultiFilter
import com.codebrig.omnisrc.observe.filter.TypeFilter
import com.google.common.collect.Sets

/**
 * todo: this
 *
 * @version 0.3
 * @since 0.3
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 */
class ElseIfConditionalFilter extends SourceNodeFilter<ElseIfConditionalFilter, Void> {

    private static final Set<String> conditionalTypes = new HashSet<>()
    static {
        conditionalTypes.add("If") //python
        conditionalTypes.add("IfStmt") //go
        conditionalTypes.add("IfStatement") //java, javascript
    }
    private final Set<Integer> elseNodeIdentities = Sets.newConcurrentHashSet()

    @Override
    boolean evaluate(SourceNode node) {
        if (node == null) {
            return false
        }
        if (node.internalType in conditionalTypes) {
            if (node.language == SourceLanguage.Python) {
                MultiFilter.matchAll(
                        new InternalRoleFilter("orelse"),
                        new TypeFilter("If.orelse")
                ).getFilteredNodes(node.children).each {
                    MultiFilter.matchAll(
                            new InternalRoleFilter("else_stmts"),
                            new TypeFilter("If")
                    ).getFilteredNodes(it.children).each {
                        new TypeFilter("If.body").getFilteredNodes(it.children).each {
                            elseNodeIdentities.add(System.identityHashCode(it.underlyingNode))
                        }
                    }
                }
            } else if (node.language == SourceLanguage.Java) {
                MultiFilter.matchAll(
                        new InternalRoleFilter("elseStatement"),
                        new TypeFilter("IfStatement")
                ).getFilteredNodes(node.children).each {
                    elseNodeIdentities.add(System.identityHashCode(it.underlyingNode))
                }
            } else if (node.language == SourceLanguage.Javascript) {
                MultiFilter.matchAll(
                        new InternalRoleFilter("alternate"),
                        new TypeFilter("IfStatement")
                ).getFilteredNodes(node.children).each {
                    elseNodeIdentities.add(System.identityHashCode(it.underlyingNode))
                }
            } else if (node.language == SourceLanguage.Go) {
                MultiFilter.matchAll(
                        new InternalRoleFilter("Else"),
                        new TypeFilter("IfStmt")
                ).getFilteredNodes(node.children).each {
                    elseNodeIdentities.add(System.identityHashCode(it.underlyingNode))
                }
            }
        }

        int nodeIdentity = System.identityHashCode(node.underlyingNode)
        if (elseNodeIdentities.contains(nodeIdentity)) {
            elseNodeIdentities.remove(nodeIdentity)
            return true
        }
        return false
    }
}