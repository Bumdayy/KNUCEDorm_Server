CREATE TABLE STUINFO (
	ID VARCHAR(14) PRIMARY KEY,
	NAME VARCHAR(20),
	PWD VARCHAR(20),
	ADDRESS VARCHAR(100),
	MAJOR VARCHAR(50),
	TELNUM VARCHAR(14),
	DORM VARCHAR(30),
	ROOMNUM VARCHAR(5)
	IMGURL VARCHAR(100));
);

CREATE TABLE SCORE (
	SCORE_NUM VARCHAR(5) PRIMARY KEY,
	ID VARCHAR(14),
	POINT VARCHAR(5),
	WHY VARCHAR(100)
);

CREATE TABLE INOUT (
	INOUT_NUM VARCHAR(5) PRIMARY KEY,
	ID VARCHAR(14),
	WHAT VARCHAR(5),
	WHEN VARCHAR(30)
);

CREATE TABLE INOUT (
	INOUT_NUM INT PRIMARY KEY,
	ID VARCHAR(14),
	NAME VARCHAR(10),
	WHAT VARCHAR(5),
	WHEN VARCHAR(30)
);

CREATE TABLE SLOUT (
	SLOUT_NUM INT PRIMARY KEY,
	ID VARCHAR(14),
	NAME VARCHAR(10),
	WHY VARCHAR(100),
	SDATE VARCHAR(30),
	EDATE VARCHAR(30)
);

DROP TABLE INOUT;
DROP TABLE SLOUT;

SELECT * FROM SLOUT;

SELECT * FROM INOUT ORDER BY inout_num DESC;

DELETE FROM SLOUT;

UPDATE SLOUT SET WHY ='����� �����~!.' WHERE SLOUT_NUM = '1';

ALTER TABLE INOUT
ADD (NAME VARCHAR(10));

SELECT NAME FROM STUINFO WHERE ID = 201605130;

SELECT * FROM INOUT;

DELETE FROM INOUT;
CREATE TABLE STUTAB (
	TAB_NUM VARCHAR(5),
	TAB_TYPE VARCHAR(5),
	TITLE VARCHAR(50),
	CONTENT VARCHAR(300),
	ID VARCHAR(14),
	SUGTYPE VARCHAR(10),
	CONSTRAINT STUTAB_PK PRIMARY KEY (TAB_NUM, TAB_TYPE) 
);


SELECT * FROM STUINFO;

SELECT * FROM TAB;

DELETE FROM STUINFO;

DELETE FROM STUINFO WHERE IMGURL is null;

DELETE FROM STUINFO WHERE id is null;

UPDATE STUINFO SET ID='201300000', PWD='5678', TELNUM='01088888888' WHERE ID = '2013129485';

ALTER TABLE STUINFO
ADD (IMGURL VARCHAR(100));

ALTER TABLE STUINFO
DROP COLUMN IMGURL;

DELETE FROM STUINFO WHERE id = '201605130';

UPDATE STUINFO SET IMGURL = 'http://bumday.dothome.co.kr/images/201605130.png' WHERE ID = '201605130';
UPDATE STUINFO SET IMGURL = 'http://bumday.dothome.co.kr/images/201605132.png' WHERE ID = '201605132';
UPDATE STUINFO SET IMGURL = 'http://bumday.dothome.co.kr/images/201501960.png' WHERE ID = '201501960';
UPDATE STUINFO SET IMGURL = 'http://bumday.dothome.co.kr/images/201501952.png' WHERE ID = '201501952';

INSERT INTO STUINFO VALUES(201605130,'�ڹ���',201605130,'�泲 ���ֽ� �Ű���', '��ǻ�Ͱ��к�', '01093445423', '�����л�', 1004);
INSERT INTO STUINFO VALUES(201605132,'������',201605132,'���� ���ֱ�����', '��ǻ�Ͱ��к�', '01034567891', 'ç�����Ͽ콺', 312);
INSERT INTO STUINFO VALUES(201501960,'������',201501960,'����Ư���� ���α�', '��ǻ�Ͱ��к�', '01077141124', '�����л�', 102);
INSERT INTO STUINFO VALUES(201501952,'�Ӽ���',201501952,'�泲 õ�Ƚ� ������', '��ǻ�Ͱ��к�', '01027286472', 'ç�����Ͽ콺', 417);

INSERT INTO STUINFO VALUES(201312345,'�����',1234,'����Ư���� ������', '�濵�а�', '01088888888', '�����л�', 709);


DROP TABLE STUINFO;