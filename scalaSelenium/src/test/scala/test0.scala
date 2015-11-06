import org.scalatest._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import selenium._

class BlogSpec extends FlatSpec with ShouldMatchers with WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  val host = "http://localhost:8080/"

  "The blog app home page" should "have the correct title" in {
    go to (host + "/")
    println(pageTitle)
    pageTitle should be ("Home - Liferay")
  }
}
  
