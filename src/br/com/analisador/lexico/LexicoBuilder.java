package br.com.analisador.lexico;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicoBuilder {

	List<Lexico> lexicos = new ArrayList<Lexico>();
	
	public void gerar(String linha, int index) {
		// Create firts step
		Lexico lexico = new Lexico();
		lexico.setNumeroLinha(index);
		
		if (linha.equals("gvar")) {
			lexico.setTipo(TipoExecucaoLexica.VALOR_VARIAVEL);
		} else if (linha.equals("gres")) {
			lexico.setTipo(TipoExecucaoLexica.VALOR_ESCOPO);
		} else if (linha.startsWith("gequal")) {
			lexico.setTipo(TipoExecucaoLexica.IGUALAR_VARIAVEL);
		}
		
		String newLinha = gerarRandoms(linha);
		
		lexico.setLinha(newLinha);
		lexicos.add(lexico);
	}
	
	public List<Lexico> getLinhas() {
		return this.lexicos;
	}
	
	private String gerarRandoms(String linha) {
		List<Random> randomValues = new ArrayList<>();
		
		Pattern randomPattern = Pattern.compile("(random)(\\()([0-9])(\\,)([0-9])(\\))");
		Matcher randomMatcher = randomPattern.matcher(linha);
		
		while (randomMatcher.find()) {
			int start = randomMatcher.start();
			int end = randomMatcher.end();
			
			Integer firts = Integer.parseInt(randomMatcher.group(3));
			Integer last = Integer.parseInt(randomMatcher.group(5));
			
			Random random = new Random();
			random.setStartValue(firts);
			random.setEndValue(last);
			random.setStrStart(start);
			random.setStrEnd(end);
			randomValues.add(random);
		}
		
		int place = 0;
		String retorno = "";
		for (Random random : randomValues) {
			int randomValue = random.generateRandom();
			retorno += linha.substring(place, random.getStrStart());
			retorno += randomValue;
			place = random.getStrEnd();
		}
		
		if (place != linha.length()) {
			retorno += linha.substring(place);
		}
		
		return retorno;
	}
	
}
