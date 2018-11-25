<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:template match="/">
        Hello
        <xsl:copy-of select="."/>
        ----------------------------------------------------
        <xsl:apply-templates select="descendant::oktatok"></xsl:apply-templates>
    </xsl:template>
    <xsl:template match="oktatok">
        <xsl:for-each select="oktato">
            <xsl:sort select="nev"/>
                <oktato> Újabb oktató: </oktato>
                Név: <xsl:value-of select="nev"/>
                Tel: <xsl:value-of select="1+2"/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>