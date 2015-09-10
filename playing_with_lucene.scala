///////////////////////////////////////////////////////////////
///PUT THIS ON YOUR BUILD.SBT FILE///
///////////////////////////////////////////////////////////////
//name := "test-gilt-lib"

//organization := "org.example"

//scalaVersion := "2.10.4"

//crossScalaVersions := Seq("2.10.4", "2.11.1")

//scalacOptions := Seq("-deprecation", "-unchecked")

//libraryDependencies ++= Seq(
  //"com.gilt" %% "lib-lucene-sugar" % "0.2.0"
//)
////////////////////////////////////////////////////////////


import com.gilt.lucene._
import org.apache.lucene.document.Document
import com.gilt.lucene.LuceneFieldHelpers._
import com.gilt.lucene.LuceneText._

val index = new ReadableLuceneIndex with WritableLuceneIndex  with LuceneStandardAnalyzer with DefaultFSLuceneDirectory 

val doc = new Document()
doc.addIndexedStoredField("string_field", "some_string aaa")
doc.addIndexedStoredField("text_field", "some text".toLuceneText)
doc.addIndexedOnlyField("optional_int", Option(42))
doc.addStoredOnlyField("long_value", 12345678L)

index.addDocument(doc)

val queryParser = index.queryParserForDefaultField("string_field")
val query = queryParser.parse("aaa")
val results = index.searchTopDocuments(query, 1)

