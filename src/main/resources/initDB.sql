CREATE TABLE students_platform.Control_Form_Dict(
                                        id              SMALLSERIAL                 PRIMARY KEY,
                                        form_name       VARCHAR(20)
);

CREATE TABLE students_platform.Subject(
                                        id              SERIAL                      PRIMARY KEY,
                                        subject_name    VARCHAR(60),
                                        control_from_id SMALLINT                    REFERENCES students_platform.Control_Form_Dict (id)
);

CREATE TABLE students_platform.Group(
                                        id              SERIAL                      PRIMARY KEY,
                                        group_name      VARCHAR(20)
);

CREATE TABLE students_platform.Role(
                                        id              SERIAL                      PRIMARY KEY,
                                        role_name       VARCHAR(20)
);


CREATE TABLE students_platform.User(
                                        id              BIGSERIAL                   PRIMARY KEY,
                                        surname         VARCHAR(60),
                                        name            VARCHAR(60),
                                        second_name     VARCHAR(60),
                                        phone_number    VARCHAR(16),
                                        email           VARCHAR(256),
                                        group_id        INT                    REFERENCES students_platform.Group (id),
                                        role_id         INT                    REFERENCES students_platform.Role (id)
);

CREATE TABLE students_platform.Subject_in_group(
                                        id              BIGSERIAL                    PRIMARY KEY,
                                        deadline        timestamp with time zone,
                                        subject_id      INT                          REFERENCES students_platform.Subject (id),
                                        group_id        BIGINT                       REFERENCES students_platform.Group (id),
                                        teacher_id      BIGINT                       REFERENCES students_platform.User (id)
);

CREATE TABLE students_platform.Task(
                                        id              BIGSERIAL                 PRIMARY KEY,
                                        task_name       VARCHAR(100),
                                        content         text,
                                        deadline        timestamp with time zone,
                                        sub_gr_id       BIGINT                    REFERENCES students_platform.Subject_in_group (id)
);

CREATE TABLE students_platform.Material(
                                        id              BIGSERIAL                 PRIMARY KEY,
                                        material_name   VARCHAR(60),
                                        file            bytea,
                                        task_id         BIGINT                  REFERENCES students_platform.Control_Form_Dict (id)
);

CREATE TABLE students_platform.Assessment(
                                        id              BIGSERIAL                 PRIMARY KEY,
                                        assessment      SMALLINT                  CHECK ( 0<= Assessment.assessment and 100<= Assessment.assessment),
                                        rating_time     timestamp with time zone,
                                        teacher_id      BIGINT                    REFERENCES students_platform.User (id),
                                        task_id         BIGINT                    REFERENCES students_platform.Task (id)

);


