CREATE TABLE Comment
(
    id        BIGINT NOT NULL AUTO_INCREMENT,
    create_at DATE   NOT NULL,
    member_id BIGINT NOT NULL,
    task_id   BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Milestone
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    end_date   DATE,
    name       VARCHAR(255) NOT NULL,
    start_date DATE,
    project_id BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Project
(
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    description       VARCHAR(255) NOT NULL,
    name              VARCHAR(255) NOT NULL,
    project_status_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE Tag
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    project_id BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Task
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    content      VARCHAR(255) NOT NULL,
    end_date     DATE,
    member_id    BIGINT,
    title        VARCHAR(255) NOT NULL,
    milestone_id BIGINT,
    project_id   BIGINT,
    PRIMARY KEY (id)
);

create table project_member
(
    member_id   bigint not null,
    project_id  bigint not null,
    member_name varchar(255),
    role        varchar(255),
    primary key (member_id, project_id)
);

create table project_status
(
    id     bigint not null auto_increment,
    status varchar(255),
    primary key (id)
);

create table task_manager
(
    member_id   bigint       not null,
    task_id     bigint       not null,
    member_name varchar(255) NOT NULL,
    primary key (member_id, task_id)
);

create table task_tag
(
    tag_id  bigint not null,
    task_id bigint not null,
    primary key (tag_id, task_id)
);

-- Rest of the table definitions

ALTER TABLE Comment
    ADD CONSTRAINT FKqcqafnfasfyuyfc23rp8ksohe FOREIGN KEY (task_id) REFERENCES Task (id);

ALTER TABLE Milestone
    ADD CONSTRAINT FK4y2imlhl4and4511uh6lhnaiy FOREIGN KEY (project_id) REFERENCES Project (id);

ALTER TABLE Project
    ADD CONSTRAINT FKio5c4tgltx908g4tm3toa0305 FOREIGN KEY (project_status_id) REFERENCES project_status (id);

ALTER TABLE Tag
    ADD CONSTRAINT FK29cp31nrrdmsqscxvv9cyhqqg FOREIGN KEY (project_id) REFERENCES Project (id);

ALTER TABLE Task
    ADD CONSTRAINT FKjl7lj35hlsnb3n8x2kyk9w5lx FOREIGN KEY (milestone_id) REFERENCES Milestone (id);

ALTER TABLE Task
    ADD CONSTRAINT FKkkcat6aybe3nbvhc54unstxm6 FOREIGN KEY (project_id) REFERENCES Project (id);

ALTER TABLE task_tag
    ADD CONSTRAINT FK49vkbl7a7bepmd8ph0w05ogaj FOREIGN KEY (tag_id) REFERENCES Tag (id);

ALTER TABLE task_tag
    ADD CONSTRAINT FK4m0s6sk9vw1ryffh72a9nyihu FOREIGN KEY (task_id) REFERENCES Task (id);