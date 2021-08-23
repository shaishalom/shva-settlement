package com.shva.settlement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the atm_transaction_rec database table.
 * 
 */
@Entity
@Table(name="atm_transaction_rec")
@NamedQuery(name="AtmTransactionRecordEntity.findAll", query="SELECT a FROM AtmTransactionRecordEntity a")
public class AtmTransactionRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique=true, nullable = false)
	private Long id;

	private int account;

	private int account_Type;

	private int amount;

	private int atm;

	private String atm_Address;

	private String ATM_Bank;

	private int atm_Branch;

	private int atm_Mikud;

	private int atm_Number;

	private int atm_Seq;

	private int auth_Num;

	private int authentication_code;

	private int BOI_issuer_id;

	private int branch;

	private int card;

	private String card_Reader;

	private int card_Retain_Reas;

	private int card_Type;

	private int commission;

	private String confirm_Code;

	private int connect_Code;

	private int cp;

	private int currency;

	private int end_Action_Code;

	private int estimat_Time_Sign;

	private String event;

	private String event_Status;

	private String flag_New;

	private String issField;

	private int issuer_id;

	private String panseq;

	private String panseq_Status;

	private int pin_Verificationcode;

	@Column(name="rec_type")
	private String recType;

	private int reply_Code;

	private String sc_Status;

	private String settlement_Flag;

	private String settlement_info;

	private int spec_Service;

	private String t2_Source;

	private int time;

	private int tp;

	private String track_2_Data;

	private int transac_Date;

	private int transac_Date_New;

	private int transm_Date;

	public AtmTransactionRecordEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getAccount_Type() {
		return this.account_Type;
	}

	public void setAccount_Type(int account_Type) {
		this.account_Type = account_Type;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAtm() {
		return this.atm;
	}

	public void setAtm(int atm) {
		this.atm = atm;
	}

	public String getAtm_Address() {
		return this.atm_Address;
	}

	public void setAtm_Address(String atm_Address) {
		this.atm_Address = atm_Address;
	}

	public String getATM_Bank() {
		return this.ATM_Bank;
	}

	public void setATM_Bank(String ATM_Bank) {
		this.ATM_Bank = ATM_Bank;
	}

	public int getAtm_Branch() {
		return this.atm_Branch;
	}

	public void setAtm_Branch(int atm_Branch) {
		this.atm_Branch = atm_Branch;
	}

	public int getAtm_Mikud() {
		return this.atm_Mikud;
	}

	public void setAtm_Mikud(int atm_Mikud) {
		this.atm_Mikud = atm_Mikud;
	}

	public int getAtm_Number() {
		return this.atm_Number;
	}

	public void setAtm_Number(int atm_Number) {
		this.atm_Number = atm_Number;
	}

	public int getAtm_Seq() {
		return this.atm_Seq;
	}

	public void setAtm_Seq(int atm_Seq) {
		this.atm_Seq = atm_Seq;
	}

	public int getAuth_Num() {
		return this.auth_Num;
	}

	public void setAuth_Num(int auth_Num) {
		this.auth_Num = auth_Num;
	}

	public int getAuthentication_code() {
		return this.authentication_code;
	}

	public void setAuthentication_code(int authentication_code) {
		this.authentication_code = authentication_code;
	}

	public int getBOI_issuer_id() {
		return this.BOI_issuer_id;
	}

	public void setBOI_issuer_id(int BOI_issuer_id) {
		this.BOI_issuer_id = BOI_issuer_id;
	}

	public int getBranch() {
		return this.branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getCard() {
		return this.card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public String getCard_Reader() {
		return this.card_Reader;
	}

	public void setCard_Reader(String card_Reader) {
		this.card_Reader = card_Reader;
	}

	public int getCard_Retain_Reas() {
		return this.card_Retain_Reas;
	}

	public void setCard_Retain_Reas(int card_Retain_Reas) {
		this.card_Retain_Reas = card_Retain_Reas;
	}

	public int getCard_Type() {
		return this.card_Type;
	}

	public void setCard_Type(int card_Type) {
		this.card_Type = card_Type;
	}

	public int getCommission() {
		return this.commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public String getConfirm_Code() {
		return this.confirm_Code;
	}

	public void setConfirm_Code(String confirm_Code) {
		this.confirm_Code = confirm_Code;
	}

	public int getConnect_Code() {
		return this.connect_Code;
	}

	public void setConnect_Code(int connect_Code) {
		this.connect_Code = connect_Code;
	}

	public int getCp() {
		return this.cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getCurrency() {
		return this.currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public int getEnd_Action_Code() {
		return this.end_Action_Code;
	}

	public void setEnd_Action_Code(int end_Action_Code) {
		this.end_Action_Code = end_Action_Code;
	}

	public int getEstimat_Time_Sign() {
		return this.estimat_Time_Sign;
	}

	public void setEstimat_Time_Sign(int estimat_Time_Sign) {
		this.estimat_Time_Sign = estimat_Time_Sign;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent_Status() {
		return this.event_Status;
	}

	public void setEvent_Status(String event_Status) {
		this.event_Status = event_Status;
	}

	public String getFlag_New() {
		return this.flag_New;
	}

	public void setFlag_New(String flag_New) {
		this.flag_New = flag_New;
	}

	public String getIssField() {
		return this.issField;
	}

	public void setIssField(String issField) {
		this.issField = issField;
	}

	public int getIssuer_id() {
		return this.issuer_id;
	}

	public void setIssuer_id(int issuer_id) {
		this.issuer_id = issuer_id;
	}

	public String getPanseq() {
		return this.panseq;
	}

	public void setPanseq(String panseq) {
		this.panseq = panseq;
	}

	public String getPanseq_Status() {
		return this.panseq_Status;
	}

	public void setPanseq_Status(String panseq_Status) {
		this.panseq_Status = panseq_Status;
	}

	public int getPin_Verificationcode() {
		return this.pin_Verificationcode;
	}

	public void setPin_Verificationcode(int pin_Verificationcode) {
		this.pin_Verificationcode = pin_Verificationcode;
	}

	public String getRecType() {
		return this.recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public int getReply_Code() {
		return this.reply_Code;
	}

	public void setReply_Code(int reply_Code) {
		this.reply_Code = reply_Code;
	}

	public String getSc_Status() {
		return this.sc_Status;
	}

	public void setSc_Status(String sc_Status) {
		this.sc_Status = sc_Status;
	}

	public String getSettlement_Flag() {
		return this.settlement_Flag;
	}

	public void setSettlement_Flag(String settlement_Flag) {
		this.settlement_Flag = settlement_Flag;
	}

	public String getSettlement_info() {
		return this.settlement_info;
	}

	public void setSettlement_info(String settlement_info) {
		this.settlement_info = settlement_info;
	}

	public int getSpec_Service() {
		return this.spec_Service;
	}

	public void setSpec_Service(int spec_Service) {
		this.spec_Service = spec_Service;
	}

	public String getT2_Source() {
		return this.t2_Source;
	}

	public void setT2_Source(String t2_Source) {
		this.t2_Source = t2_Source;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTp() {
		return this.tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public String getTrack_2_Data() {
		return this.track_2_Data;
	}

	public void setTrack_2_Data(String track_2_Data) {
		this.track_2_Data = track_2_Data;
	}

	public int getTransac_Date() {
		return this.transac_Date;
	}

	public void setTransac_Date(int transac_Date) {
		this.transac_Date = transac_Date;
	}

	public int getTransac_Date_New() {
		return this.transac_Date_New;
	}

	public void setTransac_Date_New(int transac_Date_New) {
		this.transac_Date_New = transac_Date_New;
	}

	public int getTransm_Date() {
		return this.transm_Date;
	}

	public void setTransm_Date(int transm_Date) {
		this.transm_Date = transm_Date;
	}

}