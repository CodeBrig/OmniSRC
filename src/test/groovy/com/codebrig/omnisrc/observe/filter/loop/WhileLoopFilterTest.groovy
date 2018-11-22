package com.codebrig.omnisrc.observe.filter.loop

import com.codebrig.omnisrc.OmniSRCTest
import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.observe.filter.FunctionFilter
import com.codebrig.omnisrc.observe.filter.MultiFilter
import com.codebrig.omnisrc.observe.filter.NameFilter
import gopkg.in.bblfsh.sdk.v1.protocol.generated.Encoding
import org.junit.Test

import static org.junit.Assert.*

class WhileLoopFilterTest extends OmniSRCTest {

    @Test
    void whileLoop_Go() {
        def file = new File("src/test/resources/same/loops/Loops.go")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundWhileLoop = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("whileLoop")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("whileLoop()", it.name)

            new WhileLoopFilter().getFilteredNodes(it).each {
                assertFalse(foundWhileLoop)
                foundWhileLoop = true
            }
        }
        assertTrue(foundWhileLoop)
    }

    @Test
    void whileLoop_Java() {
        def file = new File("src/test/resources/same/loops/Loops.java")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundWhileLoop = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("whileLoop")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("Loops.whileLoop()", it.name)

            new WhileLoopFilter().getFilteredNodes(it).each {
                assertFalse(foundWhileLoop)
                foundWhileLoop = true
            }
        }
        assertTrue(foundWhileLoop)
    }

    @Test
    void whileLoop_Javascript() {
        def file = new File("src/test/resources/same/loops/Loops.js")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundWhileLoop = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("whileLoop")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("whileLoop()", it.name)

            new WhileLoopFilter().getFilteredNodes(it).each {
                assertFalse(foundWhileLoop)
                foundWhileLoop = true
            }
        }
        assertTrue(foundWhileLoop)
    }

    @Test
    void whileLoop_Python() {
        def file = new File("src/test/resources/same/loops/Loops.py")
        def language = SourceLanguage.getSourceLanguage(file)
        def resp = client.parse(file.name, file.text, language.key, Encoding.UTF8$.MODULE$)

        def foundWhileLoop = false
        def functionFilter = new FunctionFilter()
        def nameFilter = new NameFilter("whileLoop")
        MultiFilter.matchAll(functionFilter, nameFilter).getFilteredNodes(language, resp.uast).each {
            assertEquals("whileLoop()", it.name)

            new WhileLoopFilter().getFilteredNodes(it).each {
                assertFalse(foundWhileLoop)
                foundWhileLoop = true
            }
        }
        assertTrue(foundWhileLoop)
    }
}