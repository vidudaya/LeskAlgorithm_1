package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

public class Lesk1 {

	public static Dictionary dictionary;
	private static  IndexWord WORD;
	 
	
	public static void main(String args[]) throws JWNLException{
		
		 dictionary = Dictionary.getDefaultResourceInstance();
		 
		 Scanner sc = new Scanner(System.in);
		 String userContext = sc.nextLine();
		 String word = sc.nextLine();
		 String meaning = leskSolve(userContext,word);
		 
		 System.out.println("Meaning = "+meaning);
	}
	
	public static String leskSolve(String userContext ,String word) throws JWNLException{
		WORD = dictionary.getIndexWord(POS.NOUN, word);
		List<String> glosses = indexedWordCheck(WORD);
		String meaning =findBestMatch(glosses,userContext.toLowerCase());
		
		return meaning;
	}
	
	public static String findBestMatch(List<String> glosses,String userContext){
		List<String> ignoreWrds = Arrays.asList("a","the", "of",  "in", "at","on");
		int sol[] = new int[glosses.size()];
		int i=-1;
		
		List<String> context = Arrays.asList(userContext.replaceAll("[^a-zA-Z ]", "").split(" "));
		
		for(String gloss : glosses){
			i++;
			List<String> gloss_  = Arrays.asList(gloss.replaceAll("[^a-zA-Z ]", "").split(" ")); 
			 
				for(String s : context){
					if(ignoreWrds.contains(s))continue;
					for(String glo : gloss_){
						if(!ignoreWrds.contains(glo) && (s.contains(glo) || glo.contains(s))){
							sol[i]++;
						}
					}
				}
			 
		}
		
		System.out.println(Arrays.toString(sol));
		int max =Integer.MIN_VALUE,pos=-1;
		for(i=0;i<sol.length;++i){
			if(max<sol[i] ){
				max=sol[i];
				pos=i;
			}
		}
		return glosses.get(pos);
	}
	
	public static List<String> indexedWordCheck(IndexWord WORD){
		
		String s = WORD.getLemma();		
		System.out.println("Lemma of the word = "+s);
		List<Synset> list = WORD.getSenses();
		List<String> glosses = new ArrayList<>();
		
		for(Synset syn : list){
			//System.out.println(syn.getGloss());
			glosses.add(syn.getGloss().toLowerCase());
		}
		return glosses;
	}
}
