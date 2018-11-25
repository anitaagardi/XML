<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="n">
        <xsl:element name="eredm">
            <xsl:element name="Customers">
                <xsl:for-each select="//Customer">
                    <xsl:sort select="CompanyName/text()"/>
                    <xsl:copy>
                        <xsl:copy-of select="@CustomerID"/>
                        <xsl:copy-of select="CompanyName"/>
                        <xsl:copy-of select="ContactName"/>
                        <xsl:copy-of select="ContactTitle"/>
                        <xsl:copy-of select="Phone"/>
                        <xsl:copy-of select="FullAddress"/>
                    </xsl:copy>
                </xsl:for-each>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    <xsl:template match="/">
        <xsl:element name="osszar">
            <xsl:for-each select="//Customer[@CustomerID/text() = root()/Root/Orders/Order[OrderDate &lt; '2010-01-01T00:00:01']/CustomerID/text()]">
                   <xsl:value-of select="sum(number(FullAddress/Price/text()))"></xsl:value-of>
           </xsl:for-each> 
        </xsl:element>
    </xsl:template>
    <xsl:template match="n">
        <xsl:for-each-group select="//Customer" group-by="FullAddress/Country">
            <xsl:element name="eredm">
                <xsl:value-of select="count(.)"></xsl:value-of>
            </xsl:element>
        </xsl:for-each-group>
    </xsl:template>
</xsl:stylesheet>