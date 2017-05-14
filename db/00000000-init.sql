--创建数据库
-- DROP DATABASE IF EXISTS wechat_checkin;
-- create DATABASE wechat_checkin IF EXISTS wechat_checkin;


--学生信息
DROP TABLE IF EXISTS "public"."studentInfo";
CREATE TABLE "public"."studentInfo" (
  "stuID" int8 NOT NULL,
  "stuName" varchar NOT NULL COLLATE "default",
  "wechatID" varchar NOT NULL COLLATE "default",
  "classID" varchar NOT NULL COLLATE "default",
  "featurePath" varchar COLLATE "default",
  "featureType" int2
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."studentInfo" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table studentInfo
-- ----------------------------
ALTER TABLE "public"."studentInfo" ADD PRIMARY KEY ("stuID") NOT DEFERRABLE INITIALLY IMMEDIATE;

--教师信息表
DROP TABLE IF EXISTS "public"."teacherInfo";
CREATE TABLE "public"."teacherInfo" (
  "teacherID" int8range NOT NULL,
  "teacherName" varchar NOT NULL COLLATE "default",
  "wechatID" varchar NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."teacherInfo" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table teacherInfo
-- ----------------------------
ALTER TABLE "public"."teacherInfo" ADD PRIMARY KEY ("teacherID") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table teacherInfo
-- ----------------------------
CREATE UNIQUE INDEX  "teacherInfo_teacherID_key" ON "public"."teacherInfo" USING btree("teacherID" "pg_catalog"."range_ops" ASC NULLS LAST);

--课程信息表
DROP TABLE IF EXISTS "public"."courseInfo";
CREATE TABLE "public"."courseInfo" (
  "courseID" int8range NOT NULL,
  "courseName" varchar NOT NULL COLLATE "default",
  "teacherID" int8range NOT NULL,
  "className" varchar NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."courseInfo" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table courseInfo
-- ----------------------------
ALTER TABLE "public"."courseInfo" ADD PRIMARY KEY ("courseID") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table courseInfo
-- ----------------------------
ALTER TABLE "public"."courseInfo" ADD CONSTRAINT "teacherID" FOREIGN KEY ("teacherID") REFERENCES "public"."teacherInfo" ("teacherID") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;


--考勤详细信息表
DROP TABLE IF EXISTS "public"."checkinDetailInfo";
CREATE TABLE "public"."checkinDetailInfo" (
  "stuID" int8range NOT NULL,
  "checkinTime" timestamp(6) NOT NULL,
  "proofPath" varchar NOT NULL COLLATE "default",
  "checkinType" int2 NOT NULL,
  "isSucc" bool NOT NULL,
  "checkinResult" int2 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."checkinDetailInfo" OWNER TO "postgres";


--考勤次序表
DROP TABLE IF EXISTS "public"."seqInfo";
CREATE TABLE "public"."seqInfo" (
  "teacherID" int8range NOT NULL,
  "courseID" int8range NOT NULL,
  "seqID" int4 NOT NULL,
  "startTime" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."seqInfo" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table seqInfo
-- ----------------------------
ALTER TABLE "public"."seqInfo" ADD PRIMARY KEY ("teacherID", "courseID") NOT DEFERRABLE INITIALLY IMMEDIATE;


