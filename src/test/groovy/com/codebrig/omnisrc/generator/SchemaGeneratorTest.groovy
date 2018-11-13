package com.codebrig.omnisrc.generator

import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.observe.ObservedLanguages
import com.codebrig.omnisrc.observe.filter.MultiFilter
import com.codebrig.omnisrc.observe.filter.TypeFilter
import com.codebrig.omnisrc.observe.filter.WhitelistRoleFilter
import com.codebrig.omnisrc.schema.grakn.GraknSchemaWriter
import org.junit.Test

import static org.junit.Assert.assertEquals

class SchemaGeneratorTest {

    @Test
    void fileAndFunctionOnlySchema() {
        def schemaGenerator = new SchemaGenerator()
        def multiFilter = new MultiFilter(MultiFilter.MatchStyle.ANY)
        def roleFilter = new WhitelistRoleFilter("FILE", "DECLARATION_FUNCTION")
        multiFilter.acceptFilter(roleFilter)
        multiFilter.acceptFilter(new TypeFilter("MethodDeclaration"))
        schemaGenerator.filter = multiFilter

        def goLanguage = schemaGenerator.observeLanguage(SourceLanguage.Go, new File("src/test/resources/same/"))
        def javaLanguage = schemaGenerator.observeLanguage(SourceLanguage.Java, new File("src/test/resources/same/"))
        def javascriptLanguage = schemaGenerator.observeLanguage(SourceLanguage.Javascript, new File("src/test/resources/same/"))
        def pythonLanguage = schemaGenerator.observeLanguage(SourceLanguage.Python, new File("src/test/resources/same/"))
        def omniLanguage = ObservedLanguages.mergeLanguages(goLanguage, javaLanguage, javascriptLanguage, pythonLanguage)
        def schemaWriter = new GraknSchemaWriter(omniLanguage, goLanguage, javaLanguage, javascriptLanguage, pythonLanguage)
        def verifyFile = new File("src/test/resources/schema/", "Same_Schema.gql")
        assertEquals(verifyFile.text, schemaWriter.getSchemaDefinition())
    }
}