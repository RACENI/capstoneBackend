package com.capstone.dao;

import com.capstone.vo.Projects;
import com.capstone.vo.ProjectsExample;
import com.capstone.vo.ProjectsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	long countByExample(ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int deleteByExample(ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int insert(ProjectsWithBLOBs row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int insertSelective(ProjectsWithBLOBs row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	List<ProjectsWithBLOBs> selectByExampleWithBLOBs(ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	List<Projects> selectByExample(ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	ProjectsWithBLOBs selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByExampleSelective(@Param("row") ProjectsWithBLOBs row, @Param("example") ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByExampleWithBLOBs(@Param("row") ProjectsWithBLOBs row, @Param("example") ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByExample(@Param("row") Projects row, @Param("example") ProjectsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByPrimaryKeySelective(ProjectsWithBLOBs row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByPrimaryKeyWithBLOBs(ProjectsWithBLOBs row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table projects
	 * @mbg.generated  Mon Nov 18 08:18:12 KST 2024
	 */
	int updateByPrimaryKey(Projects row);
}