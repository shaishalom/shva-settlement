package com.shva.settlement.task.objects;

import java.util.List;

import lombok.Data;

@Data
public class SRQOutput extends BaseBoRES {
	List<String> bank_ids;
}
