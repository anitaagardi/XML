<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<xsl:template match="Infotanszek">
	<start>
		<xsl:for-each select="tanar">
			<xsl:sort select="nev" order="descending"></xsl:sort>
			<xsl:element name="tanar">
				<xsl:text>
				</xsl:text>
				<xsl:element name="tanarnev">
					<xsl:value-of select="nev"></xsl:value-of>
				</xsl:element>
				<xsl:text>
				</xsl:text>
			</xsl:element>
		</xsl:for-each>
		<xsl:for-each select="hallgato">
			<xsl:sort select="neptunkod" order="descending"></xsl:sort>
			<xsl:element name="hallgato">
				<xsl:element name="neptunhallgato">
					<xsl:value-of select="neptunkod"></xsl:value-of>
				</xsl:element>
			</xsl:element>
		</xsl:for-each>
	</start>
</xsl:template>
</xsl:stylesheet>
				
			