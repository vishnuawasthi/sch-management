package com.sch.mngt.repository;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL92Dialect;

public class PostgresCustomDialect extends PostgreSQL92Dialect {

	
	public PostgresCustomDialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}
