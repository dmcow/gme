
package com.slm.gme.demo.domain;

import java.util.Date;

import com.slm.gme.framework.PojoDomain;

/**
 * 机构基本信息对象
 * @author zhangziwen
 *
 */
public class CocBaseInfo extends PojoDomain
{
    /**
     * 唯一序列化ID号
     */
    private static final long serialVersionUID = -1351488247797610599L;

    //唯一ID
    private Integer id;

    //机构名称
    private String orgName;

    //机构联系人
    private String contact;

    //联系电话
    private String telephone;

    //邮箱地址
    private String email;

    //区域ID列表，用逗号分隔
    private String areaId;

    //区域地址
    private String areaName;

    //详细地址
    private String address;

    //营业执照号码
    private String businessLicense;

    //营业执照图片UR
    private String businessLicenseUrl;

    //头像URL
    private String logoUrl;

    //监管行业，行业产品分类ID，逗号分割
    private String regulatoryIndustryId;

    //监管行业，行业产品分类名称
    private String regulatoryIndustryName;

    //监管区域名称
    private String regulatoryAreaName;

    //备注信息
    private String remark;

    //录入人员ID
    private Integer comRegisterId;

    //录入日期
    private Date comRegisterDate;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getBusinessLicense()
    {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense)
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseUrl()
    {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl)
    {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getLogoUrl()
    {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl)
    {
        this.logoUrl = logoUrl;
    }

    public String getRegulatoryIndustryId()
    {
        return regulatoryIndustryId;
    }

    public void setRegulatoryIndustryId(String regulatoryIndustryId)
    {
        this.regulatoryIndustryId = regulatoryIndustryId;
    }

    public String getRegulatoryIndustryName()
    {
        return regulatoryIndustryName;
    }

    public void setRegulatoryIndustryName(String regulatoryIndustryName)
    {
        this.regulatoryIndustryName = regulatoryIndustryName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getComRegisterId()
    {
        return comRegisterId;
    }

    public void setComRegisterId(Integer comRegisterId)
    {
        this.comRegisterId = comRegisterId;
    }

    public Date getComRegisterDate()
    {
        return comRegisterDate;
    }

    public void setComRegisterDate(Date comRegisterDate)
    {
        this.comRegisterDate = comRegisterDate;
    }

    public String getRegulatoryAreaName()
    {
        return regulatoryAreaName;
    }

    public void setRegulatoryAreaName(String regulatoryAreaName)
    {
        this.regulatoryAreaName = regulatoryAreaName;
    }
}
