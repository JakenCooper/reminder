package com.jaken.reminder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaken.reminder.model.WorkRecord;

@Repository("workRecordDao")
public interface WorkRecordDao extends JpaRepository<WorkRecord, String>,JpaSpecificationExecutor<WorkRecord>{

}
