

CREATE TABLE NAF6.CP_SEGUIMIENTO ( 
    SEGUIMIENTO_ID   NUMBER(8,0) NOT NULL,
    CLIENTE_ID       NUMBER(8,0) NOT NULL,
    CAMPANIA_ID      NUMBER(8,0) NOT NULL,
    ESTADO           VARCHAR2(50) NULL,
    SUBESTADO        NUMBER(8,0) NULL,
    FECHA_ULT_ESTADO DATE NOT NULL,
    USUARIO          VARCHAR2(50) NOT NULL,
    OBSERVACIONES    VARCHAR2(500) NULL,
    DURACION         NUMBER(8,0) NULL,
    LLAMANDO         VARCHAR2(5) NOT NULL,
    CONSTRAINT PK_CP_SEGUIMIENTO PRIMARY KEY(SEGUIMIENTO_ID)
)
GO
ALTER TABLE NAF6.CP_SEGUIMIENTO
    ADD ( CONSTRAINT FK_CP_SEG_CP_SUBESTADO_SEG
   FOREIGN KEY(SUBESTADO)
   REFERENCES NAF6.CP_ESTADO_SEGUIMIENTO(ESTADO_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO
ALTER TABLE NAF6.CP_SEGUIMIENTO
    ADD ( CONSTRAINT FK_CP_SEGUIMIENTO_CP_CLIENTE
   FOREIGN KEY(CLIENTE_ID)
   REFERENCES NAF6.CP_CLIENTE(CLIENTE_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO
ALTER TABLE NAF6.CP_SEGUIMIENTO
    ADD ( CONSTRAINT FK_CP_SEGUIMIENTO_CP_CAMPANIA
   FOREIGN KEY(CAMPANIA_ID)
   REFERENCES NAF6.CP_CAMPANIA(CAMPANIA_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO

CREATE TABLE NAF6.CP_SEGUIMIENTO_MENSAJE ( 
    SEG_MENSAJE_ID   NUMBER(8,0) NOT NULL,
    CLIENTE_ID       NUMBER(8,0) NOT NULL,
    CAMPANIA_ID      NUMBER(8,0) NOT NULL,
    ESTADO           VARCHAR2(50) NOT NULL,
    FECHA_ULT_ESTAD  DATE NOT NULL,
    CONSTRAINT PK_CP_SEGUIMIENTO_MENSAJE PRIMARY KEY(SEG_MENSAJE_ID)
)
GO
ALTER TABLE NAF6.CP_SEGUIMIENTO_MENSAJE
    ADD ( CONSTRAINT FK_CP_SEG_MSJ_CP_CLIENTE
   FOREIGN KEY(CLIENTE_ID)
   REFERENCES NAF6.CP_CLIENTE(CLIENTE_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO
ALTER TABLE NAF6.CP_SEGUIMIENTO_MENSAJE
    ADD ( CONSTRAINT FK_CP_SEG_MSJ_CP_CAMPANIA
   FOREIGN KEY(CAMPANIA_ID)
   REFERENCES NAF6.CP_CAMPANIA(CAMPANIA_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO

CREATE TABLE NAF6.CP_USUARIO_PERFIL ( 
    USU_PER_ID    NUMBER(8,0) NOT NULL,
    PERFIL_ID     NUMBER(8,0) NOT NULL,
    COD_USUARIO   VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_CP_USUARIOS_PERFIL PRIMARY KEY(USU_PER_ID)
)
GO
ALTER TABLE NAF6.CP_USUARIO_PERFIL
    ADD ( CONSTRAINT FK_CP_USU_PERFIL_CP_PERFIL
   FOREIGN KEY(PERFIL_ID)
   REFERENCES NAF6.CP_PERFIL(PERFIL_ID)
   NOT DEFERRABLE INITIALLY IMMEDIATE VALIDATE )
GO

CREATE SEQUENCE SECUENCIA_CP_CAMPANIAS
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_SEGUIMIENTO
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_CLIENTE
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_ESTADO_SEGUIMIENT
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_INFO_AUDIT
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_DETALLE_AUDIT
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_AUTOR
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_PERFIL
INCREMENT BY 1
START WITH 1


CREATE SEQUENCE SECUENCIA_CP_USUARIO_PERFIL
INCREMENT BY 1
START WITH 1

CREATE SEQUENCE SECUENCIA_CP_SEGUIMIENTO_MSJ
INCREMENT BY 1
START WITH 1




Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('TWILIO_APPLICATION_SID', 'TWILIO_APPLICATION_SID', 'AP93e7f675d14b0c97c963fe79f2782521');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('TWILIO_ACCOUNT_SID', 'TWILIO_ACCOUNT_SID', 'ACb9e31dc33733dd07d994fa914ba38a89');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('TWILIO_AUTH_TOKEN', 'TWILIO_AUTH_TOKEN', '8c25af29d0b0a19079415af1335004cd');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('HOURS_AFTER_LAST_STATUS', 'HOURS_AFTER_LAST_STATUS', '24');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('ACTIVE_MQ_SERVER_URL', 'ACTIVE_MQ_SERVER_URL', 'tcp://localhost:61616');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('SMS_URL', 'SMS_URL', 'sandbox.sinch.com');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('SMS_PHONE_INDICATIVE', 'SMS_PHONE_INDICATIVE', '+1');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('SMS_APP_SECRET', 'SMS_APP_SECRET', '27c4ecde-0fb8-49bc-a7a0-7c55103f9d6a');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('SMS_APP_KEY', 'SMS_APP_KEY', 'olg8mSWzlUGo3WzVF2iDNw==');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('MAIL_SERVLET_URL', 'MAIL_SERVLET_URL', 'http://186.113.233.10:8080/campaigns-web/OnloadMailServlet');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('MAIL_TEMPLATE_PATH', 'MAIL_TEMPLATE_PATH', 'C:/apps/campaigns/templates/');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('NUMBERS_MONTHS_HISTORY', 'NUMBERS_MONTHS_HISTORY', '12');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR', 'EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR', '0 0/10 * * * ?');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('FREE_CALLS_CUSTOMER_CRON_EXPR', 'FREE_CALLS_CUSTOMER_CRON_EXPR', '0 59 23 ? * *');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('MAIL_SERVER_PORT', 'MAIL_SERVER_PORT', '25');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('MAIL_SERVER_IP', 'MAIL_SERVER_IP', '192.168.1.36');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('DEFAULT_EMAIL_SENDER', 'DEFAULT_EMAIL_SENDER', 'servicioalcliente@lariviera.com');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('END_CAMPAIGNS_AND_EVENTS_CRON_EXPR', 'END_CAMPAIGNS_AND_EVENTS_CRON_EXPR', '0 59 23 ? * *');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('DEFAULT_STORE', 'DEFAULT_STORE', '1M');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('DEFAULT_COMPANY', 'DEFAULT_COMPANY', '20');
Insert into NAF6.CP_CONSTANTE
   (CONSTANTE_ID, DESCRIPCION, VALOR)
 Values
   ('DEFAULT_COUNTRY', 'DEFAULT_COUNTRY', '169');
COMMIT;

INSERT INTO CAMPAIGNS.CP_PERFIL(PERFIL_ID, NOMBRE) 
    VALUES(SECUENCIA_CP_PERFIL.nextval, 'ADMINISTRADOR')
GO

INSERT INTO CAMPAIGNS.CP_PERFIL(PERFIL_ID, NOMBRE) 
    VALUES(SECUENCIA_CP_PERFIL.nextval, 'GERENTE')
GO

INSERT INTO CAMPAIGNS.CP_PERFIL(PERFIL_ID, NOMBRE) 
    VALUES(SECUENCIA_CP_PERFIL.nextval, 'COMERCIAL')
GO




// permisos por perfil

INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_INSERT')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_UPDATE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_SEARCH')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_START')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_REPORT_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'AUTHORS_ADMIN_INSERT')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'AUTHORS_ADMIN_UPDATE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'AUTHORS_ADMIN_DELETE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'AUTHORS_ADMIN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'MY_CAMPAIGN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_DELETE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (1, 'CAMPAIGN_ADMIN_APROVE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_ADMIN_INSERT')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_ADMIN_UPDATE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_ADMIN_SEARCH')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_ADMIN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_START')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'CAMPAIGN_REPORT_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'AUTHORS_ADMIN_INSERT')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'AUTHORS_ADMIN_UPDATE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'AUTHORS_ADMIN_DELETE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'AUTHORS_ADMIN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (3, 'MY_CAMPAIGN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_ADMIN_UPDATE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_ADMIN_DELETE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_ADMIN_APROVE')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_ADMIN_SEARCH')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_ADMIN_VIEW')
GO
INSERT INTO CP_PERMISO_PERFIL VALUES (2, 'CAMPAIGN_REPORT_VIEW')

//INSERT USUARIO GENERICO C051
GO
INSERT INTO CAMPAIGNS.CP_USUARIO_PERFIL(USU_PER_ID, PERFIL_ID, COD_USUARIO) 
    VALUES(SECUENCIA_CP_USUARIO_PERFIL.nextval, 1, 'C051')
