Markdown
# Kassasystem (Inlämning 2)

Ett objektorienterat kassasystem byggt i Java.
Gränssnittet är utvecklat med Java Swing.

Projektet visar grundläggande principer inom objektorienterad programmering (OOP):
* **Arv** och **polymorfism**
* **Inkapsling** och **ansvarsfördelning**

---

## 🏗️ Arkitektur och ansvarsfördelning

Projektet är uppdelat i flera klasser.
Varje klass har ett tydligt och avgränsat ansvar:

* **`Main`**
  Programmets startpunkt.
  Startar det grafiska gränssnittet säkert via `SwingUtilities.invokeLater`.

* **`KassaFonster`**
  Hanterar det grafiska gränssnittet (GUI).
  Skapar snabbvalsknappar dynamiskt från en produktarray.
  Hanterar inmatning av antal och skriver ut kvittot i textfältet.

* **`Produkt`**
  Basklass för alla varor.
  Håller reda på egenskaper som `namn` och `pris`.
  Innehåller logiken för momsberäkning.

* **`Tidning`**
  Subklass som ärver (`extends`) från `Produkt`.
  Använder polymorfism (`@Override`) för sin momssats.
  Sätter en unik momssats på 6 % istället för standarden.

---

## 🛠️ Tekniker och verktyg

* **Språk:** Java
* **GUI:** Java Swing
* **Byggverktyg:** Maven
* **Versionshantering:** Git & GitHub