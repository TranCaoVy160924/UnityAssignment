using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyRun : MonoBehaviour
{
    public float moveSpeed = 2f; // speed of movement
    public float moveDistance = 1f; // distance to move before turning around
    private float startPosX; // starting position of enemy
    private bool movingRight = true; // flag to determine direction of movement

    void Start()
    {
        startPosX = transform.position.x; // set starting position of enemy
    }

    void Update()
    {
        // move the enemy left or right based on movingRight flag
        if (movingRight)
        {
            transform.Translate(Vector2.right * moveSpeed * Time.deltaTime);
        }
        else
        {
            transform.Translate(Vector2.left * moveSpeed * Time.deltaTime);
        }

        // if the enemy has moved the specified distance, turn around
        if (Mathf.Abs(transform.position.x - startPosX) >= moveDistance)
        {
            movingRight = !movingRight; // flip direction
            transform.localScale = new Vector2(-transform.localScale.x, transform.localScale.y); // flip sprite
        }
    }
}