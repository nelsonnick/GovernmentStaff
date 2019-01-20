package com.wts.entity.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("department", "id", Department.class);
		arp.addMapping("department_err", "id", DepartmentErr.class);
		arp.addMapping("json", "id", Json.class);
		arp.addMapping("person", "id", Person.class);
		arp.addMapping("person_err", "id", PersonErr.class);
	}
}

