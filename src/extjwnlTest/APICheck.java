package extjwnlTest;

import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.data.Synset;

public class APICheck {
	
	private static  IndexWord DOG;
	private static  IndexWord CAT;
	public static void main(String args[]) throws JWNLException{
		
		Dictionary dictionary = Dictionary.getDefaultResourceInstance();
		DOG = dictionary.getIndexWord(POS.NOUN, "dog");
		CAT = dictionary.lookupIndexWord(POS.NOUN, "cat");
		//indexedWordCheck(DOG);
		lookupIndexWordCheck(CAT);
	}
	
	public static void indexedWordCheck(IndexWord WORD){
		
		String s = WORD.getLemma();		
		System.out.println("Lemma of the word = "+s);
		List<Synset> list = WORD.getSenses();
		List<String> glosses = new ArrayList<>();
		
		for(Synset syn : list){
			System.out.println(syn.getGloss());
			glosses.add(syn.getGloss());
		}
	}
	
	public static void lookupIndexWordCheck(IndexWord WORD){
		String s = WORD.getLemma();		
		System.out.println("Lemma of the word = "+s);
		List<Synset> list = WORD.getSenses();
		List<String> glosses = new ArrayList<>();
		
		for(Synset syn : list){
			System.out.println(syn.getGloss());
			glosses.add(syn.getGloss());
		}
	}
	 
}
