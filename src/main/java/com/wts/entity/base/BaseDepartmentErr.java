package com.wts.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDepartmentErr<M extends BaseDepartmentErr<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setSzcs(java.lang.String szcs) {
		set("szcs", szcs);
	}
	
	public java.lang.String getSzcs() {
		return getStr("szcs");
	}

	public void setDwzd(java.lang.String dwzd) {
		set("dwzd", dwzd);
	}
	
	public java.lang.String getDwzd() {
		return getStr("dwzd");
	}

	public void setDwlb(java.lang.String dwlb) {
		set("dwlb", dwlb);
	}
	
	public java.lang.String getDwlb() {
		return getStr("dwlb");
	}

	public void setDwlx(java.lang.String dwlx) {
		set("dwlx", dwlx);
	}
	
	public java.lang.String getDwlx() {
		return getStr("dwlx");
	}

	public void setSjdw(java.lang.String sjdw) {
		set("sjdw", sjdw);
	}
	
	public java.lang.String getSjdw() {
		return getStr("sjdw");
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

	public void setBase(java.lang.String base) {
		set("base", base);
	}
	
	public java.lang.String getBase() {
		return getStr("base");
	}

	public void setTime(java.lang.String time) {
		set("time", time);
	}
	
	public java.lang.String getTime() {
		return getStr("time");
	}

}
