<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:fn="http://www.w3.org/2005/xpath-functions"
xmlns:e="beadando"
>
	<xsl:template match="e:Zöldseges">
		<html>
			<head></head>
			<body>
				<h1>XSL lekérdezések</h1>
				<h2>1.Szállítók nevei és hogy milyen zöldségeket szállítottak</h2>
				<table border="1">
					<tr>
						<th>Szállitok neve</th>
						<th>Szállitott termek neve</th>
					</tr>
					<xsl:for-each select="e:szallito">
						<tr>
							<td><xsl:value-of select="e:nev" /></td>
							<xsl:variable name="termekkod" select="@termek_vonalkod" />
							<xsl:for-each select="../e:termek">
								<xsl:if test="$termekkod = @vonalkod">
									<td><xsl:value-of select="e:nev" /></td>
								</xsl:if>
							</xsl:for-each>
						</tr>		
					</xsl:for-each>
				</table>
				<br />
				<h2>2.Azok az alkalmazottak akik Miskolcon laknak és mellé hogy ott is születtek-e</h2>
				<table border="1">
					<tr>
						<th>Miskolci alkalmazottak</th>
						<th>Ott született?</th>
					</tr>
					<xsl:for-each select="e:alkalmazott[e:lakcim/e:varos = 'Miskolc']">
						<tr>
							<td><xsl:value-of select="e:nev/e:vezeteknev" /> &#160;
								<xsl:value-of select="e:nev/e:keresztnev" /></td>
							
							<xsl:choose>
								<xsl:when test="e:szuletesi_hely = 'Miskolc'">
									<td>Igen</td>
								</xsl:when>
								<xsl:when test="e:szuletesi_hely != 'Miskolc'">
									<td>Nem</td>
								</xsl:when>
							</xsl:choose>
					</tr>
					</xsl:for-each>
					</table>
					
				<br />
				<h2>3.22évnél idősebb alkalmazottak szigsz-a és neve</h2>
				<table border="1">
					<tr>
						<th>Sz.i.szám</th>
						<th>Név</th>
					</tr>
					<xsl:for-each select="e:alkalmazott">
						<xsl:if test="e:szuletesi_datum/e:ev &lt; 1990">
						<tr>
							<td><xsl:value-of select="@alkalmazott_szigsz" /></td>
							<td>
								<xsl:value-of select="e:nev/e:vezeteknev" />
								<xsl:value-of select="e:nev/e:keresztnev" />
							</td>
						</tr>	
						</xsl:if>
					</xsl:for-each>
				</table>
				
				
				<br /><h2>4.Lejárt termékek vonalkódja</h2>
				<xsl:for-each select="e:termek">
					<xsl:if test="e:ervenyesseg/e:ev &lt; 2013">
						<xsl:if test="12 >= e:ervenyesseg/e:honap">
							<xsl:if test="12 >= e:ervenyesseg/e:nap">
								<xsl:value-of select="@vonalkod" /><br />
							</xsl:if>
						</xsl:if>
					</xsl:if>
				</xsl:for-each>
				
				<br /><h2>5.Budapesti vásárlók neve</h2>
				<xsl:for-each select="e:vasarlo">
					<xsl:if test="e:lakcim/e:varos='Budapest'">
						<xsl:value-of select="e:nev/e:vezeteknev" />&#160;
						<xsl:value-of select="e:nev/e:keresztnev" />
						<br />
					</xsl:if>
				</xsl:for-each>
				
				<br /><h2>6.A zöldségek nevei és származási helye</h2>
				<table border="1">
					<tr>
						<th>Termék neve</th>
						<th>Származási hely</th>
					</tr>
				<xsl:for-each select="e:termek">
					<tr>
						<td>
							<xsl:value-of select="e:nev" />
						</td>
						<td>
							<xsl:value-of select="e:szarmazasi_hely" />
						</td>
					</tr>
				</xsl:for-each>
				</table>
				
				<br /><h2>7.Melyik termékből(név) van a legkevesebb db</h2>
				<xsl:variable name="mini" select="min(e:termek/e:darabszam)" />
				<xsl:for-each select="e:termek">
					<xsl:if test="e:darabszam = $mini">
						<xsl:value-of select="e:nev" /><br />
					</xsl:if>
				</xsl:for-each>
				
				<br /><h2>8.Vásárlók neve városonként</h2>
				<table border="1">
					<tr>
						<th>Név</th>
						<th>Város</th>
					</tr>
				<xsl:for-each select="e:vasarlo">
					<tr>
						<td>
							<xsl:value-of select="e:nev" />
						</td>
						<td>
							<xsl:value-of select="e:lakcim/e:varos" />
						</td>
					</tr>
				</xsl:for-each>
				</table>
				
				<br /><h2>9.Vásárlók szigszáma és megrendelt termékek valamint a rendelési dátum (hónap/nap)</h2>
				<table border="1">
					<tr>
						<th>Vásárló</th>
						<th>Termék neve</th>
						<th>Rendelési dátum</th>
					</tr>
				<xsl:for-each select="e:rendeles">
					<tr>
						<xsl:variable name="termek_id" select="@termekref" />
						<xsl:variable name="vasarlo_id" select="@vasarloref" />
						<td><xsl:value-of select="$vasarlo_id" /></td>
						<xsl:for-each select="../e:termek">
							<xsl:if test="@vonalkod = $termek_id">
								<td>
									<xsl:value-of select="e:nev" />
								</td>
							</xsl:if>
						</xsl:for-each> 
						<td>
						<xsl:value-of select="e:datum/e:honap" />.<xsl:value-of select="e:datum/e:nap" />
						</td>
					</tr>
				</xsl:for-each>
				</table>
				
				<br /><h2>10.Azok a zöldségek vonalkódja és darabszáma amiknek a származási helye Magyarország</h2>
				<table border="1">
					<tr>
						<th>Vonalkód</th>
						<th>Darabszám</th>
					</tr>
				<xsl:for-each select="e:termek[e:szarmazasi_hely='Magyarország']">
					<tr>
						<td><xsl:value-of select="@vonalkod" /></td>
						<td><xsl:value-of select="e:darabszam" /></td>
					</tr>
				</xsl:for-each>
				</table>
				
				<h2>11.A főnök beosztású alkalmazottak neve és lakcime</h2>
				<table border="1">
				<tr>
					<th>Név</th>
					<th>Lakcim</th>
				</tr>
				<xsl:for-each select="e:alkalmazott[e:beosztas='főnök']">
					<tr>
						<td>
							<xsl:value-of select="e:nev/e:vezeteknev" />&#160;
							<xsl:value-of select="e:nev/e:keresztnev" />
						</td>
						<td>
							<xsl:value-of select="e:lakcim/e:varos" />,&#160;
							<xsl:value-of select="e:lakcim/e:utca" />&#160;
							<xsl:value-of select="e:lakcim/e:hazszam" />
						</td>
					</tr>
				</xsl:for-each>
				</table>
				<br /><small>Opera böngészőben tesztelve</small>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>