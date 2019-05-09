package com.codebrig.arthur.observe.structure.filter.operator.misc

import com.codebrig.arthur.SourceNode
import com.codebrig.arthur.observe.structure.StructureFilter
import com.codebrig.arthur.observe.structure.filter.MultiFilter
import com.codebrig.arthur.observe.structure.filter.RoleFilter

/**
 * Match by ternary operator
 *
 * @version 0.4
 * @since 0.3
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 */
class TernaryOperatorFilter extends StructureFilter<TernaryOperatorFilter, Void> {

    private final MultiFilter ternaryOperatorFilter

    TernaryOperatorFilter() {
        MultiFilter ternaryToken1Filter = MultiFilter.matchAll(
                new RoleFilter("IF"), new RoleFilter("EXPRESSION"), new RoleFilter("ASSIGNMENT"),
                new RoleFilter("BINARY"), new RoleFilter("RIGHT"),
                new RoleFilter("CONDITION")
        )
        MultiFilter ternaryToken2Filter = MultiFilter.matchAll(
                new RoleFilter("IF"), new RoleFilter("EXPRESSION"), new RoleFilter("ASSIGNMENT"),
                new RoleFilter("BINARY"), new RoleFilter("RIGHT")
        )
        this.ternaryOperatorFilter = MultiFilter.matchAny(ternaryToken1Filter, ternaryToken2Filter)
    }

    @Override
    boolean evaluate(SourceNode node) {
        return this.ternaryOperatorFilter.evaluate(node)
    }
}
