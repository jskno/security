package com.jskno.myeazybankbackendapp.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="loans")
@Getter
@Setter
public class Loan {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	@Column(name = "loan_number")
	private Long loanNumber;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name="start_dt")
	private Date startDt;
	
	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "total_loan")
	private Integer totalLoan;
	
	@Column(name = "amount_paid")
	private Integer amountPaid;
	
	@Column(name = "outstanding_amount")
	private Integer outstandingAmount;
	
	@Column(name = "create_dt")
	private String createDt;
}
