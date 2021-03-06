;;This file autogenerated from 
;;
;;  src/cljx/c2/layout/partition.cljx
;;
(ns c2.layout.partition (:refer-clojure :exclude [partition]))
(defn partition [root & {:keys [sort children value size output-key], :or {sort nil, children :children, value :value, size [1 1], output-key :partition}}] (defn depth [node] (inc (if-let [cs (children node)] (apply max (map depth cs)) 0))) (defn node-value [node] (if-let [cs (children node)] (apply + (map node-value cs)) (value node))) (defn position [node depth x [dx dy]] (concat [(assoc node output-key (merge (output-key node) {:depth depth, :value (node-value node), :x x, :y (* depth dy), :dx dx, :dy dy}))] (let [unit-cdx (/ dx (node-value node)) cs (children node)] (flatten (map (fn [child cx] (position child (inc depth) cx [(* unit-cdx (node-value child)) dy])) cs (reductions (fn [cx child] (+ cx (* unit-cdx (node-value child)))) x cs)))))) (position root 0 0 [(first size) (/ (second size) (depth root))]))