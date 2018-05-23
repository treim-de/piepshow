package de.treim.piepshow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;


public class ImpressumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressum);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impressum");
        ((TextView)findViewById(R.id.tvimpressum)).setText(Html.fromHtml("Biologische Station Rhein-Berg<br/>\n" +
                "Kammerbroich 67<br/>\n" +
                "51503 R&#246;srath<br/>\n" +
                "Tel: 02205 9498940<br/>\n" +
                "Fax: 02205 94989499<br/>\n" +
                "<a href=\"mailto:Rhein-Berg@BS-BL.de\">Rhein-Berg@BS-BL.de</a>\n" +
                "<p><strong>Registereintrag:</strong><br/>\n" +
                "Eingetragen im Vereinsregister<br/>\n" +
                "Registergericht: Amtsgericht K&#246;ln<br/>\n" +
                "Registernummer: VR 17245</p>\n" +
                "</p>\n" +
                "<meta name=\"generator\" content=\"Impressum-Generator der Kanzlei Hensche Rechtsanwälte\"/>\n" +
                "<p>&nbsp;</p>\n" +
                "<h1>Disclaimer - rechtliche Hinweise</h1>\n" +
                "<p><strong>Auskunfts- und Widerrufsrecht</strong></p>\n" +
                "<p>Sie haben jederzeit das Recht, sich unentgeltlich und unverz&#252;glich &#252;ber die zu Ihrer Person erhobenen Daten \n" +
                "zu erkundigen. Ebenfalls k&#246;nnen Sie Ihre Zustimmung zur Verwendung Ihrer angegebenen pers&#246;nlichen Daten mit \n" +
                "Wirkung f&#252;r die Zukunft widerrufen. Hierf&#252;r wenden Sie sich bitte an den im Impressum angegebenen \n" +
                "Diensteanbieter.</p>\n" +
                "<P><STRONG>Datenschutz (allgemein)</STRONG></P>\n" +
                "<P>Beim Zugriff auf unsere Webseite werden automatisch allgemeine Informationen (sog. Server-Logfiles) erfasst. Diese \n" +
                "beinhalten u.a. den von Ihnen verwendeten Webbrowser sowie Ihr Betriebssystem und Ihren Internet Service Provider. Diese \n" +
                "Daten lassen keinerlei R&#252;ckschl&#252;sse auf Ihre Person zu und werden von uns statistisch ausgewertet, um unseren \n" +
                "Internetauftritt technisch und inhaltlich zu verbessern. Das Erfassen dieser Informationen ist notwendig, um den Inhalt \n" +
                "der Webseite korrekt ausliefern zu k&#246;nnen.</P>\n" +
                "<P>Die Nutzung der Webseite ist grunds&#228;tzlich ohne Angabe personenbezogener Daten m&#246;glich. Soweit \n" +
                "personenbezogene Daten (beispielsweise Name, Anschrift oder E-Mail-Adressen) erhoben werden, erfolgt dies, soweit \n" +
                "m&#246;glich, stets auf freiwilliger Basis. Diese Daten werden ohne Ihre ausdr&#252;ckliche Zustimmung nicht an Dritte \n" +
                "weitergegeben.</P>\n" +
                "<P>Sofern ein Vertragsverh&#228;ltnis begr&#252;ndet, inhaltlich ausgestaltet oder ge&#228;ndert werden soll oder Sie an \n" +
                "uns eine Anfrage stellen, erheben und verwenden wir personenbezogene Daten von Ihnen, soweit dies zu diesem Zwecke \n" +
                "erforderlich ist (Bestandsdaten). Wir erheben, verarbeiten und nutzen personenbezogene Daten soweit dies erforderlich \n" +
                "ist, um Ihnen die Inanspruchnahme des Webangebots zu erm&#246;glichen (Nutzungsdaten). S&#228;mtliche personenbezogenen \n" +
                "Daten werden nur solange gespeichert wie dies f&#252;r den genannten Zweck (Bearbeitung Ihrer Anfrage oder Abwicklung \n" +
                "eines Vertrags) erforderlich ist. Hierbei werden steuer- und handelsrechtliche Aufbewahrungsfristen von uns \n" +
                "ber&#252;cksichtigt. Auf Anordnung der zust&#228;ndigen Stellen m&#252;ssen wir im Einzelfall Auskunft &#252;ber diese \n" +
                "Daten (Bestandsdaten) erteilen, soweit dies f&#252;r Zwecke der Strafverfolgung, zur Gefahrenabwehr, zur Erf&#252;llung \n" +
                "der gesetzlichen Aufgaben der Verfassungsschutzbeh&#246;rden oder des Milit&#228;rischen Abschirmdienstes oder zur \n" +
                "Durchsetzung der Rechte am geistigen Eigentum erforderlich ist.</P>\n" +
                "<P>Wir weisen ausdr&#252;cklich darauf hin, dass die Daten&#252;bertragung im Internet (z. B. bei der Kommunikation per \n" +
                "E-Mail) Sicherheitsl&#252;cken aufweisen kann. Vor dem Zugriff auf Daten kann nicht l&#252;ckenlos gesch&#252;tzt \n" +
                "werden.</P>\n" +
                "<P>Die Nutzung von im Rahmen der Impressumspflicht ver&#246;ffentlichten Kontaktdaten durch Dritte zur &#220;bersendung \n" +
                "von nicht ausdr&#252;cklich angeforderter Werbung und Informationsmaterialien wird hiermit ausdr&#252;cklich untersagt. \n" +
                "Ausgenommen hiervon sind bestehende Gesch&#228;ftsbeziehungen bzw. es liegt Ihnen eine entsprechende Einwilligung von uns \n" +
                "vor.</P>\n" +
                "<P>Die Anbieter und alle auf dieser Website genannten Dritten behalten sich ausdr&#252;cklich rechtliche Schritte im \n" +
                "Falle der unverlangten Zusendung von Werbeinformationen vor. Gleiches gilt f&#252;r die kommerzielle Verwendung und \n" +
                "Weitergabe der Daten.</P>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><strong>Disclaimer (Haftungsausschluss)</strong></p>\n" +
                "<p><strong>1. Haftung f&#252;r Inhalte</strong></p>\n" +
                "<p>Als Diensteanbieter sind wir gem&#228;&#223; &#167; 7 Abs. 1 TMG f&#252;r eigene Inhalte auf diesen Seiten nach den \n" +
                "allgemeinen Gesetzen verantwortlich. Nach &#167;&#167; 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht \n" +
                "verpflichtet, &#252;bermittelte oder gespeicherte fremde Informationen zu &#252;berwachen oder nach Umst&#228;nden zu \n" +
                "forschen, die auf eine rechtswidrige T&#228;tigkeit hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung \n" +
                "von Informationen nach den allgemeinen Gesetzen bleiben hiervon unber&#252;hrt. Eine diesbez&#252;gliche Haftung ist \n" +
                "jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung m&#246;glich. Bei Bekanntwerden von \n" +
                "entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.</p>\n" +
                "<p><strong>3. Urheberrecht</strong></p>\n" +
                "<p>Die durch die Diensteanbieter, deren Mitarbeiter und beauftragte Dritte erstellten Inhalte und Werke auf diesen Seiten \n" +
                "unterliegen dem deutschen Urheberrecht. Die Vervielf&#228;ltigung, Bearbeitung, Verbreitung und jede Art der Verwertung \n" +
                "au&#223;erhalb der Grenzen des Urheberrechtes bed&#252;rfen der vorherigen schriftlichen Zustimmung des jeweiligen Autors \n" +
                "bzw. Erstellers. Downloads und Kopien dieser Seite sind nur f&#252;r den privaten, nicht kommerziellen Gebrauch \n" +
                "gestattet. Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter \n" +
                "beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine \n" +
                "Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von \n" +
                "Rechtsverletzungen werden derartige Inhalte umgehend entfernen.</p>\n" +
                "<p>&nbsp;</p>"));
    }
}
