package br.com.analisador.lexico;

public class Random {
	
	private Integer startValue;
	
	private Integer endValue;
	
	private Integer strStart;
	
	private Integer strEnd;

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getEndValue() {
		return endValue;
	}

	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}

	public Integer getStrStart() {
		return strStart;
	}

	public void setStrStart(Integer strStart) {
		this.strStart = strStart;
	}

	public Integer getStrEnd() {
		return strEnd;
	}

	public void setStrEnd(Integer strEnd) {
		this.strEnd = strEnd;
	}
	
	public Integer generateRandom() {
		double randomValue = 0;
		
		if (startValue > endValue) randomValue = Math.floor(Math.random() * (startValue - endValue) + endValue);
		else if (startValue < endValue) randomValue = Math.floor(Math.random() * (endValue - startValue) + startValue);
		
		return (int) randomValue;
	}

}
