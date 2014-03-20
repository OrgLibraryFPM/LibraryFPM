package com.lib.fpm.util;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("unchecked")
public abstract class SpecUtility {
	
	private static final String NMASK = "9999999999";
	private static final String TO_CHAR_FN = "to_char";
	private static final char LIKE_OP = '%';

	private static String like(Object value) {
		return new StringBuilder().append(LIKE_OP).append(value).append(LIKE_OP).toString();
	}

	// @off;
	public static <T, S, N extends Number> Specification<T> createNumberLike(
			final SingularAttribute<? super S, N> attribute, 
			final Object value) {

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return createNumberPredicate(root, query, cb, attribute, value);
			}
		};
	}

	public static <T, S> Specification<T> createStringLike(
			final SingularAttribute<? super S, String> attribute, 
			final Object value) {

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return createStringPredicate(root, query, cb, attribute, value);
			}
		};
	}

	public static <T, S, N extends Number> Specification<T> createNumberLike(
			final SingularAttribute<? super T, ? super S> rootAttribute,
			final SingularAttribute<? super S, N> leafAttribute,
			final Object value) {

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return createNumberPredicate(root, query, cb, rootAttribute, leafAttribute, value);
			}
		};
	}

	public static <T, S, Y> Specification<T> createStringLike(
			final SingularAttribute<? super S, ? super Y> rootAttribute,
			final SingularAttribute<? super Y, String> leafAttribute, 
			final Object value) {

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return createStringPredicate(root, query, cb, rootAttribute, leafAttribute, value);
			}
		};
	}


	public static <T, S> Specification<T> hasValue(
			final SingularAttribute< ? super S , ? > attribute, 
			final Object value) {

		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get((SingularAttribute<? super T, ?>) attribute), value );
			}
		};
	}

	public static <T, S, N> Predicate createNumberPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb,
			final SingularAttribute<? super S, N> attribute, 
			final Object value ){

			Expression<String> fn = cb.function(TO_CHAR_FN,	String.class, 
				root.get((SingularAttribute<? super T, N>) attribute), cb.literal(NMASK)
			);

			return cb.like(cb.lower(fn), SpecUtility.like(value).toLowerCase());
	}

	public static <T, S, N extends Number> Predicate createNumberPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb,
			final SingularAttribute<? super T, ? super S> rootAttribute,
			final SingularAttribute<? super S, N > leafAttribute,
			final Object value ){

			Expression<String> fn = cb.function(TO_CHAR_FN,	String.class, 
				root.get((SingularAttribute<? super T, S>) rootAttribute).get(leafAttribute), cb.literal(NMASK)
			);

			return cb.like(cb.lower(fn), SpecUtility.like(value).toLowerCase());
	}

	public static <T, S> Predicate createStringPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb,
			final SingularAttribute<? super S, String> attribute, 
			final Object value){
		return cb.like(cb.lower(root.get((SingularAttribute<? super T, String>) attribute)), 
				SpecUtility.like(value).toLowerCase()
			);
	}

	public static <T, S, Y> Predicate createStringPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, 
			final SingularAttribute<? super S, ? super Y> rootAttribute,
			final SingularAttribute<? super Y, String> leafAttribute, 
			final Object value){

		return cb.like(cb.lower(root.get((SingularAttribute<? super T, Y>) rootAttribute).get(leafAttribute)), 
				SpecUtility.like(value).toLowerCase()
			);
	}

}
