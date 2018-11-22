package com.codebrig.omnisrc.observe.filter.conditional

import com.codebrig.omnisrc.OmniSRCTest
import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.observe.filter.FunctionFilter
import com.codebrig.omnisrc.observe.filter.MultiFilter
import com.codebrig.omnisrc.observe.filter.NameFilter
import gopkg.in.bblfsh.sdk.v1.protocol.generated.Encoding
import org.junit.Test

import static org.junit.Assert.*

class SwitchCaseConditionalFilterTest extends OmniSRCTest {

    @Test
    void switchCaseConditional_Go() {
        def file = new File("src/test/resources/same/conditionals/Conditionals.go")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundSwitchCaseConditional = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("switchCaseConditional")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("switchCaseConditional()", it.name)

            new SwitchCaseConditionalFilter().getFilteredNodes(it).each {
                assertFalse(foundSwitchCaseConditional)
                foundSwitchCaseConditional = true
            }
        }
        assertTrue(foundSwitchCaseConditional)
    }

    @Test
    void switchCaseConditional_Java() {
        def file = new File("src/test/resources/same/conditionals/Conditionals.java")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundSwitchCaseConditional = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("switchCaseConditional")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("Conditionals.switchCaseConditional()", it.name)

            new SwitchCaseConditionalFilter().getFilteredNodes(it).each {
                assertFalse(foundSwitchCaseConditional)
                foundSwitchCaseConditional = true
            }
        }
        assertTrue(foundSwitchCaseConditional)
    }

    @Test
    void switchCaseConditional_Javascript() {
        def file = new File("src/test/resources/same/conditionals/Conditionals.js")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundSwitchCaseConditional = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("switchCaseConditional")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("switchCaseConditional()", it.name)

            new SwitchCaseConditionalFilter().getFilteredNodes(it).each {
                assertFalse(foundSwitchCaseConditional)
                foundSwitchCaseConditional = true
            }
        }
        assertTrue(foundSwitchCaseConditional)
    }
}