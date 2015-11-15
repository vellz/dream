package com.dream.mapper;

import com.dream.model.UserOrder;
import com.dream.model.UserOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int countByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int deleteByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int insert(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int insertSelective(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	List<UserOrder> selectByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	UserOrder selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int updateByExampleSelective(@Param("record") UserOrder record,
			@Param("example") UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int updateByExample(@Param("record") UserOrder record,
			@Param("example") UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int updateByPrimaryKeySelective(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Nov 14 22:19:40 CST 2015
	 */
	int updateByPrimaryKey(UserOrder record);
}