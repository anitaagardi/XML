<?xml version="1.0"?>
<!-- greeting.xsl -->
<xsl:stylesheet version="2.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mav="http://iit.uni-miskolc.hu/sema/mav">
 <xsl:output method="html" indent="yes" encoding="UTF-8"/>
 <xsl:template match="/">
 <xsl:element name="html">
            <xsl:element name="head">
              <xsl:element name="title">
			    3. beadandó feladat
			 </xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="h1">
				 Ellenőrök és büntetéseik
                 </xsl:element>
                    <xsl:element name="table">
					   <xsl:element name="thead">
						</xsl:element>
						<xsl:element name="tr">
							<xsl:element name="th">
								Ellenőr neve
							</xsl:element>
							<xsl:element name="th">
								Megbüntetett utas neve
							</xsl:element>
							<xsl:element name="th">
								Büntetés összege
							</xsl:element>
						</xsl:element>
					  <xsl:apply-templates select="mav:adatbazis/mav:ellenor"/>
					</xsl:element>
            </xsl:element>
        </xsl:element>	

 </xsl:template>
 
 <xsl:template match="mav:ellenor">
   
   
   <xsl:variable name="azon" select="mav:buntetes/@kit"/>
   <xsl:variable name="ellenornev" select="concat(mav:nev/mav:vezeteknev/text(),',&#x20;',mav:nev/mav:keresztnev/text())"/>
   <xsl:variable name="osszeghelyben" select="mav:buntetes/mav:helyben/text()"/>
   <xsl:variable name="osszegcsekken" select="mav:buntetes/mav:csekken/text()"/>
   <xsl:for-each select="../mav:utas[@id = $azon]">
	   <xsl:element name="tr">
	     <xsl:element name="td">
	     <xsl:value-of select="$ellenornev"/>
		  </xsl:element>
	     <xsl:element name="td">
	     <xsl:value-of select="concat(mav:nev/mav:vezeteknev/text(),',&#x20;',mav:nev/mav:keresztnev/text())"/>
		  </xsl:element>
		
		 <xsl:element name="td">
		 <xsl:attribute name="style">
		    text-align:right;
		 </xsl:attribute>
	     <xsl:value-of select="concat($osszegcsekken,'&#x20;Ft')"/>
		  </xsl:element>

	   </xsl:element>

    </xsl:for-each>  
 </xsl:template>
 

</xsl:stylesheet>