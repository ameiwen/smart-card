package com.zx.card.model;

import java.util.Date;

public class BorrowRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.card_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private Long cardId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.book_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private Long bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.type_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private Long typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.username
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.event_type
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private String eventType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.status
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.option_user
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private String optionUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_borrow_record.create_time
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.id
     *
     * @return the value of sc_borrow_record.id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.id
     *
     * @param id the value for sc_borrow_record.id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.card_id
     *
     * @return the value of sc_borrow_record.card_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public Long getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.card_id
     *
     * @param cardId the value for sc_borrow_record.card_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.book_id
     *
     * @return the value of sc_borrow_record.book_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.book_id
     *
     * @param bookId the value for sc_borrow_record.book_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.type_id
     *
     * @return the value of sc_borrow_record.type_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.type_id
     *
     * @param typeId the value for sc_borrow_record.type_id
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.username
     *
     * @return the value of sc_borrow_record.username
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.username
     *
     * @param username the value for sc_borrow_record.username
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.event_type
     *
     * @return the value of sc_borrow_record.event_type
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.event_type
     *
     * @param eventType the value for sc_borrow_record.event_type
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.status
     *
     * @return the value of sc_borrow_record.status
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.status
     *
     * @param status the value for sc_borrow_record.status
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.option_user
     *
     * @return the value of sc_borrow_record.option_user
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public String getOptionUser() {
        return optionUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.option_user
     *
     * @param optionUser the value for sc_borrow_record.option_user
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setOptionUser(String optionUser) {
        this.optionUser = optionUser == null ? null : optionUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_borrow_record.create_time
     *
     * @return the value of sc_borrow_record.create_time
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_borrow_record.create_time
     *
     * @param createTime the value for sc_borrow_record.create_time
     *
     * @mbggenerated Wed Apr 29 17:03:03 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}