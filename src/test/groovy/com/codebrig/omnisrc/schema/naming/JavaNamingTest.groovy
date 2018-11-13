package com.codebrig.omnisrc.schema.naming

import com.codebrig.omnisrc.OmniSRCTest
import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.observe.filter.TypeFilter
import com.codebrig.omnisrc.observe.filter.WhitelistRoleFilter
import gopkg.in.bblfsh.sdk.v1.protocol.generated.Encoding
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class JavaNamingTest extends OmniSRCTest {

    @Test
    void fileQualifiedName_noPackage() {
        def file = new File("src/test/resources/java/ForStmt.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def fileFilter = new WhitelistRoleFilter("FILE")

        fileFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            assertEquals("ForStmt", it.qualifiedName)
        }
    }

    @Test
    void fileQualifiedName_withPackage() {
        def file = new File("src/test/resources/java/com/company/ForStmt_WithPackage.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def fileFilter = new WhitelistRoleFilter("FILE")

        fileFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            assertEquals("com.company.ForStmt", it.qualifiedName)
        }
    }

    @Test
    void methodQualifiedName_noPackage() {
        def file = new File("src/test/resources/java/ForStmt.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            assertEquals("ForStmt.method()", it.qualifiedName)
        }
    }

    @Test
    void methodQualifiedName_withPackage() {
        def file = new File("src/test/resources/java/com/company/ForStmt_WithPackage.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            assertEquals("com.company.ForStmt.method()", it.qualifiedName)
        }
    }

    @Test
    void methodQualifiedName_variousStuff_noPackage() {
        def file = new File("src/test/resources/java/VariousStuff.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        boolean foundMethod3 = false
        boolean foundMethod4 = false
        boolean foundMethod5 = false
        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            switch (it.qualifiedName) {
                case "VariousStuff.method_3_args(java.lang.String,int,java.lang.Object)":
                    foundMethod3 = true
                    break
                case "VariousStuff.method_4_args(byte,java.lang.String,int,java.lang.Object)":
                    foundMethod4 = true
                    break
                case "VariousStuff.method_5_args(byte,java.lang.String,int,java.lang.Object,char)":
                    foundMethod5 = true
                    break
                default:
                    throw new IllegalArgumentException("Invalid qualified name: " + it.qualifiedName)
            }
        }
        assertTrue(foundMethod3)
        assertTrue(foundMethod4)
        assertTrue(foundMethod5)
    }

    @Test
    void methodQualifiedName_variousStuff_withPackage() {
        def file = new File("src/test/resources/java/com/company/VariousStuff.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        boolean foundMethod3 = false
        boolean foundMethod4 = false
        boolean foundMethod5 = false
        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            switch (it.qualifiedName) {
                case "com.company.VariousStuff.method_3_args(java.lang.String,int,java.lang.Object)":
                    foundMethod3 = true
                    break
                case "com.company.VariousStuff.method_4_args(byte,java.lang.String,int,java.lang.Object)":
                    foundMethod4 = true
                    break
                case "com.company.VariousStuff.method_5_args(byte,java.lang.String,int,java.lang.Object,char)":
                    foundMethod5 = true
                    break
                default:
                    throw new IllegalArgumentException("Invalid qualified name: " + it.qualifiedName)
            }
        }
        assertTrue(foundMethod3)
        assertTrue(foundMethod4)
        assertTrue(foundMethod5)
    }

    @Test
    void methodQualifiedName_importQualified_noPackage() {
        def file = new File("src/test/resources/java/ImportQualifiedName.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        boolean foundSetMethod = false
        boolean foundMapMethod = false
        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            switch (it.qualifiedName) {
                case "ImportQualifiedName.acceptGuavaSet(com.google.common.collect.ImmutableSet<java.lang.Boolean>)":
                    foundSetMethod = true
                    break
                case "ImportQualifiedName.acceptGuavaMap(com.google.common.collect.ImmutableMap<java.lang.Boolean,java.lang.Integer>)":
                    foundMapMethod = true
                    break
                default:
                    throw new IllegalArgumentException("Invalid qualified name: " + it.qualifiedName)
            }
        }
        assertTrue(foundSetMethod)
        assertTrue(foundMapMethod)
    }

    @Test
    void methodQualifiedName_importQualified_withPackage() {
        def file = new File("src/test/resources/java/com/company/ImportQualifiedName.java")
        def resp = client.parse(file.name, file.text, SourceLanguage.Java.key(), Encoding.UTF8$.MODULE$)
        def functionFilter = new TypeFilter("MethodDeclaration")

        boolean foundSetMethod = false
        boolean foundMapMethod = false
        functionFilter.getFilteredNodes(SourceLanguage.Java, resp.uast).each {
            switch (it.qualifiedName) {
                case "com.company.ImportQualifiedName.acceptGuavaSet(com.google.common.collect.ImmutableSet<java.lang.Boolean>)":
                    foundSetMethod = true
                    break
                case "com.company.ImportQualifiedName.acceptGuavaMap(com.google.common.collect.ImmutableMap<java.lang.Boolean,java.lang.Integer>)":
                    foundMapMethod = true
                    break
                default:
                    throw new IllegalArgumentException("Invalid qualified name: " + it.qualifiedName)
            }
        }
        assertTrue(foundSetMethod)
        assertTrue(foundMapMethod)
    }
}
