package com.sforce.soap.partner;

/**
 * Generated by ComplexTypeCodeGenerator.java. Please do not edit.
 */
public interface IDescribeQuickActionListItemResult  {

      /**
       * element : accessLevelRequired of type {urn:partner.soap.sforce.com}ShareAccessLevel
       * java type: com.sforce.soap.partner.ShareAccessLevel
       */

      public com.sforce.soap.partner.ShareAccessLevel getAccessLevelRequired();

      public void setAccessLevelRequired(com.sforce.soap.partner.ShareAccessLevel accessLevelRequired);

      /**
       * element : colors of type {urn:partner.soap.sforce.com}DescribeColor
       * java type: com.sforce.soap.partner.DescribeColor[]
       */

      public com.sforce.soap.partner.IDescribeColor[] getColors();

      public void setColors(com.sforce.soap.partner.IDescribeColor[] colors);

      /**
       * element : iconUrl of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getIconUrl();

      public void setIconUrl(java.lang.String iconUrl);

      /**
       * element : icons of type {urn:partner.soap.sforce.com}DescribeIcon
       * java type: com.sforce.soap.partner.DescribeIcon[]
       */

      public com.sforce.soap.partner.IDescribeIcon[] getIcons();

      public void setIcons(com.sforce.soap.partner.IDescribeIcon[] icons);

      /**
       * element : label of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getLabel();

      public void setLabel(java.lang.String label);

      /**
       * element : miniIconUrl of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getMiniIconUrl();

      public void setMiniIconUrl(java.lang.String miniIconUrl);

      /**
       * element : quickActionName of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getQuickActionName();

      public void setQuickActionName(java.lang.String quickActionName);

      /**
       * element : targetSobjectType of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getTargetSobjectType();

      public void setTargetSobjectType(java.lang.String targetSobjectType);

      /**
       * element : type of type {http://www.w3.org/2001/XMLSchema}string
       * java type: java.lang.String
       */

      public java.lang.String getType();

      public void setType(java.lang.String type);


}
