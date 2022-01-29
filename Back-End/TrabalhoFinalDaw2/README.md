Atualizar dados do application.properties
~~~SQL
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.jwt.expiration=86400000
spring.jwt.secret=jwt.secret=rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\}x3?JR3.2zr~v)gYF^8\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\J?N,nvH.<2\.r~w]*e~vgak)X"v8H`MH/7"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/
~~~

SQL :

Tabela Usuario
~~~SQL
INSERT INTO usuario(
    id, email, nome, senha)
    VALUES (1, 'emailfake@gmail.com', 'Admin', 'admin');
 ~~~
 Tabela Perfil
 ~~~SQL
INSERT INTO perfil(
    id, nome)
    VALUES (1, ROLE_ADMIN);
 ~~~
 Tabela usuario_perfil
 ~~~SQL
INSERT INTO usuario_perfis(
    cliente_id, perfil_id)
    VALUES (1, 1);
 ~~~
