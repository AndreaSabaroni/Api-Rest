create table dbo.ADN (
	id bigint identity not null,
	adn VARCHAR(max) NULL,
	mutanet bit NOT NULL,
	constraint PK_ADN primary key (id)
)