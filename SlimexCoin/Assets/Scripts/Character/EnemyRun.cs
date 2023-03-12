using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyRun : MonoBehaviour
{
    public float speed = 2f; // the speed at which the enemy moves
    public float distance = 1f; // the distance the enemy moves left and right
    private bool movingRight = true; // whether the enemy is currently moving right or left
    private Vector3 startPosition; // the starting position of the enemy

    void Start()
    {
        startPosition = transform.position;
    }

    void Update()
    {
        // check if the enemy has reached its maximum distance in either direction
        if (transform.position.x > startPosition.x + distance)
        {
            movingRight = false;
        }
        else if (transform.position.x < startPosition.x - distance)
        {
            movingRight = true;
        }

        // move the enemy left or right based on its current direction
        if (movingRight)
        {
            transform.position = new Vector3(transform.position.x + speed * Time.deltaTime, transform.position.y, transform.position.z);
            Flip();
        }
        else
        {
            transform.position = new Vector3(transform.position.x - speed * Time.deltaTime, transform.position.y, transform.position.z);
            Flip();

        }

        void Flip()
        {
            Vector3 currentScale = gameObject.transform.localScale;
            currentScale.x *= -1;
            gameObject.transform.localScale = currentScale;
        }
    }
}
