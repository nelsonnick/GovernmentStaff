package com.wts.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePerson<M extends BasePerson<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setDwbh(java.lang.String dwbh) {
		set("dwbh", dwbh);
	}
	
	public java.lang.String getDwbh() {
		return getStr("dwbh");
	}

	public void setDwmc(java.lang.String dwmc) {
		set("dwmc", dwmc);
	}
	
	public java.lang.String getDwmc() {
		return getStr("dwmc");
	}

	public void setSsbm(java.lang.String ssbm) {
		set("ssbm", ssbm);
	}
	
	public java.lang.String getSsbm() {
		return getStr("ssbm");
	}

	public void setRyxm(java.lang.String ryxm) {
		set("ryxm", ryxm);
	}
	
	public java.lang.String getRyxm() {
		return getStr("ryxm");
	}

	public void setRyxb(java.lang.String ryxb) {
		set("ryxb", ryxb);
	}
	
	public java.lang.String getRyxb() {
		return getStr("ryxb");
	}

	public void setBzlx(java.lang.String bzlx) {
		set("bzlx", bzlx);
	}
	
	public java.lang.String getBzlx() {
		return getStr("bzlx");
	}

	public void setBzqk(java.lang.String bzqk) {
		set("bzqk", bzqk);
	}
	
	public java.lang.String getBzqk() {
		return getStr("bzqk");
	}

}
