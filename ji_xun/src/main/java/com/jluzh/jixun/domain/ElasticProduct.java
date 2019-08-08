package com.jluzh.jixun.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "pro", type = "product",shards = 1,replicas = 0)
public class ElasticProduct {
		@Id
	 	private Long id;
		
		@Field(type=FieldType.Long,index=false)
	    private Long userId;
		
	    private Long productCategoryId;
	    
	    @Field(type=FieldType.Text,analyzer = "ik_max_word")
	    private String name;
	    
	    private String pic;
	    
	    private Integer newStatus;
	    
	    private Integer recommandStatus;

	    private Integer sort;

	    private BigDecimal price;
	    
	    @Field(type=FieldType.Text,analyzer = "ik_max_word")
	    private String subTitle;

	    private Integer stock;
	    
	    @Field(type=FieldType.Text,analyzer = "ik_max_word")
	    private String keywords;
	    private String productCategoryName;
	    private String nickname;
	    private String time;
	    
		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getTime() {
			return time;
		}

		public void setTime(Date time) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.time = sdf.format(time);;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getProductCategoryId() {
			return productCategoryId;
		}

		public void setProductCategoryId(Long productCategoryId) {
			this.productCategoryId = productCategoryId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public Integer getNewStatus() {
			return newStatus;
		}

		public void setNewStatus(Integer newStatus) {
			this.newStatus = newStatus;
		}

		public Integer getRecommandStatus() {
			return recommandStatus;
		}

		public void setRecommandStatus(Integer recommandStatus) {
			this.recommandStatus = recommandStatus;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getSubTitle() {
			return subTitle;
		}

		public void setSubTitle(String subTitle) {
			this.subTitle = subTitle;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getProductCategoryName() {
			return productCategoryName;
		}

		public void setProductCategoryName(String productCategoryName) {
			this.productCategoryName = productCategoryName;
		}
	    
	    
	    
}
