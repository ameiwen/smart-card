package com.zx.card.dao.generate;

import com.zx.card.model.BookType;
import com.zx.card.model.BookTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int countByExample(BookTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int deleteByExample(BookTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int insert(BookType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int insertSelective(BookType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    List<BookType> selectByExample(BookTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    BookType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int updateByExampleSelective(@Param("record") BookType record, @Param("example") BookTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int updateByExample(@Param("record") BookType record, @Param("example") BookTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int updateByPrimaryKeySelective(BookType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_book_type
     *
     * @mbggenerated Wed Apr 29 10:25:19 CST 2020
     */
    int updateByPrimaryKey(BookType record);
}