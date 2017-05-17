--创建数据库
-- DROP DATABASE IF EXISTS wechat_checkin;
-- create DATABASE wechat_checkin OWNER postgres;

--微信用户表
DROP TABLE IF EXISTS "public"."wechat_user_info";
CREATE TABLE "public"."wechat_user_info" (
  "wechat_id" varchar NOT NULL COLLATE "default",
  "user_info_id" varchar NOT NULL COLLATE "default",
  "user_type" int2 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."wechat_user_info" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table wechatUserInfo
-- ----------------------------
ALTER TABLE "public"."wechat_user_info" ADD PRIMARY KEY ("wechat_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

--学生信息
DROP TABLE IF EXISTS "public"."student_info";
CREATE TABLE "public"."student_info" (
  "stu_id" int8 NOT NULL,
  "stu_name" varchar NOT NULL COLLATE "default",
  "wechat_id" varchar NOT NULL COLLATE "default",
  "class_id" varchar NOT NULL COLLATE "default",
  "feature_path" varchar COLLATE "default",
  "feature_type" int2
)
WITH (OIdS=FALSE);
ALTER TABLE "public"."student_info" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table studentInfo
-- ----------------------------
ALTER TABLE "public"."student_info" ADD PRIMARY KEY ("stu_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

--教师信息表
DROP TABLE IF EXISTS "public"."teacher_info";
CREATE TABLE "public"."teacher_info" (
  "teacher_id" int8 NOT NULL,
  "teacher_name" varchar NOT NULL COLLATE "default",
  "wechat_id" varchar NOT NULL COLLATE "default"
)
WITH (OIdS=FALSE);
ALTER TABLE "public"."teacher_info" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table teacherInfo
-- ----------------------------
ALTER TABLE "public"."teacher_info" ADD PRIMARY KEY ("teacher_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table teacherInfo
-- ----------------------------
CREATE UNIQUE INDEX  "teacher_info_teacher_id_key" ON "public"."teacher_info" USING btree("teacher_id" "pg_catalog"."range_ops" ASC NULLS LAST);

--课程信息表
DROP TABLE IF EXISTS "public"."course_info";
CREATE TABLE "public"."course_info" (
  "course_id" int8 NOT NULL,
  "course_name" varchar NOT NULL COLLATE "default",
  "teacher_id" int8 NOT NULL,
  "class_name" varchar NOT NULL COLLATE "default"
)
WITH (OIdS=FALSE);
ALTER TABLE "public"."course_info" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table courseInfo
-- ----------------------------
ALTER TABLE "public"."course_info" ADD PRIMARY KEY ("course_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table courseInfo
-- ----------------------------
ALTER TABLE "public"."course_info" ADD CONSTRAINT "teacher_id" FOREIGN KEY ("teacher_id") REFERENCES "public"."teacher_info" ("teacher_id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;


--考勤详细信息表
DROP TABLE IF EXISTS "public"."checkin_detail_info";
CREATE TABLE "public"."checkin_detail_info" (
  "stu_id" int8 NOT NULL,
  "checkin_time" timestamp(6) NOT NULL,
  "proof_path" varchar NOT NULL COLLATE "default",
  "checkin_type" int2 NOT NULL,
  "is_succ" bool NOT NULL,
  "checkin_result" int2 NOT NULL
)
WITH (OIdS=FALSE);
ALTER TABLE "public"."checkin_detail_info" OWNER TO "postgres";


--考勤次序表
DROP TABLE IF EXISTS "public"."seq_info";
CREATE TABLE "public"."seq_info" (
  "teacher_id" int8 NOT NULL,
  "course_id" int8 NOT NULL,
  "seq_id" int4 NOT NULL,
  "start_time" timestamp(6) NOT NULL
)
WITH (OIdS=FALSE);
ALTER TABLE "public"."seq_info" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table seqInfo
-- ----------------------------
ALTER TABLE "public"."seq_info" ADD PRIMARY KEY ("teacher_id", "course_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

