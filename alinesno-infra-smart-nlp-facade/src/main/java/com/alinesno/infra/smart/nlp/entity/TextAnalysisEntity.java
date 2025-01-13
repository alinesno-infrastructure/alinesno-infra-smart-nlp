package com.alinesno.infra.smart.nlp.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Data
@TableName("text_analysis")
public class TextAnalysisEntity extends InfraBaseEntity {
	/**
	 * 文本内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("文本内容")
	@TableField("text_content")
	private String textContent;

	/**
	 * 分析类型Id
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("分析类型Id")
	@TableField("analysis_type_id")
	private Long analysisTypeId;

	/**
	 * 类别 Id
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("类别 Id")
	@TableField("category_id")
	private Long categoryId;

	/**
	 * 观点
	 */
	@ColumnType(length = 20)
	@ColumnComment("观点")
	@TableField("sentiment")
	private String sentiment;

	/**
	 * 关键字
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("关键字")
	@TableField("keywords")
	private String keywords;

	/**
	 * 实体类型ID
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("实体类型ID")
	@TableField("entity_type_id")
	private Long entityTypeId;

	/**
	 * 实体名称
	 */
	@ColumnType(length = 100)
	@ColumnComment("实体名称")
	@TableField("entity_name")
	private String entityName;

	/**
	 * 摘要内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("摘要内容")
	@TableField("summary_content")
	private String summaryContent;

	/**
	 * 文本1
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("文本1")
	@TableField("text1")
	private String text1;

	/**
	 * 文本2
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("文本2")
	@TableField("text2")
	private String text2;

	/**
	 * 相似性核心
	 */
	@ColumnType(MySqlTypeConstant.FLOAT)
	@ColumnComment("相似性核心")
	@TableField("similarity_score")
	private Float similarityScore;

	/**
	 * 问题
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("问题")
	@TableField("question")
	private String question;

	/**
	 * 答复
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("答复")
	@TableField("answer")
	private String answer;

	/**
	 * 源语言ID
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("源语言ID")
	@TableField("source_language_id")
	private Long sourceLanguageId;

	/**
	 * 目标语言 Id
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("目标语言 Id")
	@TableField("target_language_id")
	private Long targetLanguageId;

	/**
	 * 翻译文本内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("翻译文本内容")
	@TableField("translated_text_content")
	private String translatedTextContent;

	/**
	 * 生成的文本内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("生成的文本内容")
	@TableField("generated_text_content")
	private String generatedTextContent;

	/**
	 * 生成时间
	 */
	@ColumnType(value = MySqlTypeConstant.DATETIME, length = 18)
	@ColumnComment("生成时间")
	@TableField("generation_time")
	private Date generationTime;


}
