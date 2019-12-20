create table dbo.ADN (
	id bigint identity not null,
	cadenaADN VARCHAR(max) NULL,
	mutante bit NOT NULL,
	constraint PK_ADN primary key (id)
)