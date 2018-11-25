<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="n">
        <xsl:value-of select="count(//konyv)"/>
    </xsl:template>
    <xsl:template match="n">
        <xsl:element name="szakacskonyvek_masolva">
            <xsl:copy-of select="//konyv[kategoria='szakacs']"/>
        </xsl:element>
    </xsl:template>
    <xsl:template match="n">
        <xsl:for-each select="//kiado">
            <xsl:element name="kiadok_nevei">
                <xsl:value-of select="nev"/>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="n">
        <xsl:for-each select="//konyv[kategoria='vers']">
            <xsl:element name="cim">
                <xsl:value-of select="cim"/>
            </xsl:element>
            <xsl:element name="ar">
                <xsl:value-of select="ar"/>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="n">
        <xsl:element name="eredmeny">
            <xsl:value-of select="avg(//konyv[kategoria='szakacs']/number(ar))"/>
        </xsl:element>
    </xsl:template>
    <xsl:template match="n">
        <xsl:element name="eredm">
            <xsl:for-each-group select="//konyv" group-by="kategoria">
                <xsl:element name="kategoria">
                    <xsl:value-of select="current-grouping-key()"/>
                </xsl:element>
                <xsl:element name="atlag">
                    <xsl:value-of select="avg(ar)"/>
                </xsl:element>
            </xsl:for-each-group>
        </xsl:element>
    </xsl:template>
    <xsl:template match="n">
        <xsl:element name="eredm">
            <xsl:for-each-group select="//konyv" group-by="kategoria">
                <xsl:element name="kategoria">
                    <xsl:value-of select="current-grouping-key()"/>
                </xsl:element>
                <xsl:element name="darab">
                    <xsl:value-of select="count(number(ar) &gt; 20)"/>
                </xsl:element>
            </xsl:for-each-group>
        </xsl:element>
    </xsl:template>
    <xsl:template match="n">
        <xsl:element name="varosok">
            <xsl:copy-of select="//kiado/varos/text()"/>
        </xsl:element>
    </xsl:template>
    <xsl:template match="/">
        <xsl:element name="eredm">
            <xsl:copy-of select="//konyv"></xsl:copy-of>
            <xsl:for-each select="//kiado">
                <xsl:element name="kiado">
                    <xsl:copy-of select="@kod"/>
                    <xsl:copy-of select="nev"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>