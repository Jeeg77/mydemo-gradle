package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.entity.Book;

public class SortingItems {
	
	public static List<Book> mergeBooks(Iterable<Book> books){
		
		List<Book> libri = iterableToList(books);		 
		
        return mergeSort(libri, Comparator.comparingInt(Book::getYear));
	}
	
	
	public static Set<Book> setBooks(Iterable<Book> books) {		
		Set<Book> libri = iterableToSet(books);
		return libri;
	}
	
	public static Map<Integer, Book> mapBooks(Iterable<Book> books) {		
		Map<Integer, Book> libri = iterableToMap(books);
		return libri;
	}
	
	
	private static <T> List<T> mergeSort(List<T> lista, Comparator<T> comparator) {
		
		if (lista.size() <= 1) 
			return lista;
		
		int mid          = lista.size() / 2;	
		List<T> left  = mergeSort(lista.subList(0, mid), comparator);
		List<T> right = mergeSort(lista.subList(mid, lista.size()), comparator);

        return merge(left, right, comparator);
	}
	
	
	private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
	    List<T> result = new ArrayList<>();
	    int i = 0, j = 0;

	    while (i < left.size() && j < right.size()) {
	        if (comparator.compare(left.get(i), right.get(j)) <= 0) {
	            result.add(left.get(i++));
	        } else {
	            result.add(right.get(j++));
	        }
	    }

	    while (i < left.size()) result.add(left.get(i++));
	    while (j < right.size()) result.add(right.get(j++));

	    return result;
	}
	
	private static <T> List<T> iterableToList(Iterable<T> iterable) {
		System.out.println("Collezione di libri, struttura dati utilizzata: List/ArrayList");
	    List<T> list = new ArrayList<>();
	    iterable.forEach(list::add);
	    return list;
	}
	
	private static <T> Set<T> iterableToSet(Iterable<T> iterable) {
		System.out.println("Collezione di libri, struttura dati utilizzata: Set/HashSet");
		Set<T> set = new HashSet<>();
		iterable.forEach(set::add);
		return set;
	}
	
	private static <K,V> Map<Integer,V> iterableToMap(Iterable<V> iterable) {
		System.out.println("Collezione di libri, struttura dati utilizzata: Map/HashMap");
		Map<Integer,V> map = new HashMap<>();
		int[] i = {0};
		iterable.forEach(entry -> map.put(i[0]++, entry));
		return map;
	}
	

}
